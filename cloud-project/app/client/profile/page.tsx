'use client'

import { useState, useEffect } from 'react'
import { useQuery, useMutation, gql } from '@apollo/client'
import { useForm } from 'react-hook-form'
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Button } from "@/components/ui/button"
import { Textarea } from "@/components/ui/textarea"
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import { useToast } from "@/hooks/use-toast"
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert"
import { AlertCircle } from 'lucide-react'
import Link from 'next/link'

const GET_CLIENT_PROFILE = gql`
    query GetClientProfile($correo: String!) {
        clienteByCorreo(correo: $correo) {
            id
            nombre
            correo
            edad
            foto
            descripcion
        }
    }
`

const UPDATE_CLIENT_PROFILE = gql`
    mutation UpdateClientProfile($correo: String!, $cliente: ClienteInput!) {
        updateClienteData(correo: $correo, cliente: $cliente) {
            descripcion
            foto
        }
    }
`

const GET_CLIENT_SERVICES = gql`
    query GetClientServices($correo: String!) {
        alojamientosByCliente(clienteCorreo: $correo) {
            id
            fechaCheckIn
            fechaCheckOut
            comentario
            calificacion
            alojamiento {
                id
                nombre
                ubicacion
                precioPorNoche
            }
        }
        transportesByCliente(clienteCorreo: $correo) {
            id
            numeroPlaca
            comentario
            calificacion
            transporte {
                id
                origen
                destino
                precio
            }
        }
    }
`

const RATE_ALOJAMIENTO = gql`
    mutation RateAlojamiento($alojamientoId: ID!, $clienteCorreo: String!, $calificacion: Float!, $comentario: String!) {
        CalificacionYComentarioAlojamiento(alojamientoId: $alojamientoId, clienteCorreo: $clienteCorreo, calificacion: $calificacion, comentario: $comentario) {
            calificacion
            comentario
        }
    }
`

const RATE_TRANSPORTE = gql`
    mutation RateTransporte($transporteId: ID!, $clienteCorreo: String!, $calificacion: Float!, $comentario: String!) {
        CalificacionYComentarioTransporte(transporteId: $transporteId, clienteCorreo: $clienteCorreo, calificacion: $calificacion, comentario: $comentario) {
            calificacion
            comentario
        }
    }
`

export default function ClientProfilePage() {
    const { toast } = useToast()
    const [imageFile, setImageFile] = useState<File | null>(null)
    const [clientEmail, setClientEmail] = useState('')
    const [ratings, setRatings] = useState({})
    const [comments, setComments] = useState({})

    useEffect(() => {
        const userCookie = document.cookie.split('; ').find(row => row.startsWith('user='))
        if (userCookie) {
            const userData = JSON.parse(userCookie.split('=')[1])
            setClientEmail(userData.email)
        }
    }, [])

    const { data, loading, error, refetch } = useQuery(GET_CLIENT_PROFILE, {
        variables: { correo: clientEmail },
        skip: !clientEmail,
        onError: (error) => {
            console.error('Error fetching client profile:', error)
        }
    })

    const { data: servicesData, loading: servicesLoading, error: servicesError } = useQuery(GET_CLIENT_SERVICES, {
        variables: { correo: clientEmail },
        skip: !clientEmail,
        onError: (error) => {
            console.error('Error fetching client services:', error)
        }
    })

    const [updateProfile] = useMutation(UPDATE_CLIENT_PROFILE)
    const [rateAlojamiento] = useMutation(RATE_ALOJAMIENTO)
    const [rateTransporte] = useMutation(RATE_TRANSPORTE)

    const { register, handleSubmit, setValue } = useForm()

    useEffect(() => {
        if (data?.clienteByCorreo) {
            setValue('descripcion', data.clienteByCorreo.descripcion)
        }
    }, [data, setValue])

    const onSubmit = async (formData) => {
        try {
            let fotoUrl = data?.clienteByCorreo?.foto

            if (imageFile) {
                const uploadFormData = new FormData()
                uploadFormData.append('file', imageFile)
                const response = await fetch('/api/upload', {
                    method: 'POST',
                    body: uploadFormData,
                })
                const result = await response.json()
                fotoUrl = result.url
            }

            await updateProfile({
                variables: {
                    correo: clientEmail,
                    cliente: {
                        foto: fotoUrl,
                        descripcion: formData.descripcion,
                    },
                },
            })

            toast({ description: "Perfil actualizado con éxito" })
            refetch()
        } catch (error) {
            console.error('Error updating profile:', error)
            toast({ variant: "destructive", description: "Error al actualizar el perfil" })
        }
    }

    const handleRate = async (serviceId: string, serviceType: 'alojamiento' | 'transporte') => {
        try {
            const rating = parseFloat(ratings[serviceId])
            const comment = comments[serviceId]

            if (!rating || isNaN(rating)) {
                toast({ variant: "destructive", description: "Por favor, ingrese una calificación válida" })
                return
            }

            console.log("ServiceId:", serviceId)
            console.log("ServiceType:", serviceType)
            console.log("clienteCorreo:", clientEmail)

            if (serviceType === 'alojamiento') {
                await rateAlojamiento({
                    variables: {
                        alojamientoId: serviceId,
                        clienteCorreo: clientEmail,
                        calificacion: rating,
                        comentario: comment || '',
                    },
                })
            } else {
                await rateTransporte({
                    variables: {
                        transporteId: serviceId,
                        clienteCorreo: clientEmail,
                        calificacion: rating,
                        comentario: comment || '',
                    },
                })
            }
            toast({ description: "Calificación enviada con éxito" })
            // Clear the rating and comment for this service
            setRatings(prev => ({ ...prev, [serviceId]: undefined }))
            setComments(prev => ({ ...prev, [serviceId]: undefined }))
        } catch (error) {
            console.error('Error rating service:', error)
            toast({ variant: "destructive", description: "Error al enviar la calificación" })
        }
    }

    if (loading) return <p>Cargando perfil...</p>
    if (error) {
        return (
            <Alert variant="destructive">
                <AlertCircle className="h-4 w-4" />
                <AlertTitle>Error</AlertTitle>
                <AlertDescription>
                    Error al cargar el perfil: {error.message}
                    <br />
                    Por favor, intenta recargar la página. Si el problema persiste, contacta al soporte técnico.
                </AlertDescription>
            </Alert>
        )
    }
    if (!data || !data.clienteByCorreo) {
        return (
            <Alert>
                <AlertCircle className="h-4 w-4" />
                <AlertTitle>Información no disponible</AlertTitle>
                <AlertDescription>
                    No se encontró información del cliente. Esto puede deberse a un problema de conexión o a que el perfil aún no ha sido creado.
                </AlertDescription>
            </Alert>
        )
    }

    const { nombre, correo, edad, foto, descripcion } = data.clienteByCorreo

    return (
        <div className="container mx-auto p-4">
            <Card>
                <CardHeader>
                    <CardTitle>Perfil del Cliente</CardTitle>
                </CardHeader>
                <CardContent>
                    <div className="space-y-8">
                        <div className="flex items-center space-x-4">
                            <Avatar className="w-24 h-24">
                                <AvatarImage src={foto} alt={nombre} />
                                <AvatarFallback>{nombre?.charAt(0)}</AvatarFallback>
                            </Avatar>
                            <div>
                                <h2 className="text-2xl font-bold">{nombre}</h2>
                                <p className="text-gray-500">{correo}</p>
                                <p className="text-gray-500">{edad ? `${edad} años` : ''}</p>
                            </div>
                        </div>

                        <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
                            <div>
                                <Label htmlFor="foto">Foto de perfil</Label>
                                <Input
                                    id="foto"
                                    type="file"
                                    accept="image/*"
                                    onChange={(e) => setImageFile(e.target.files?.[0] || null)}
                                />
                            </div>

                            <div>
                                <Label htmlFor="descripcion">Descripción</Label>
                                <Textarea
                                    id="descripcion"
                                    {...register('descripcion')}
                                    defaultValue={descripcion}
                                />
                            </div>

                            <Button type="submit">Actualizar Perfil</Button>
                        </form>

                        <Tabs defaultValue="alojamientos">
                            <TabsList>
                                <TabsTrigger value="alojamientos">Alojamientos</TabsTrigger>
                                <TabsTrigger value="transportes">Transportes</TabsTrigger>
                            </TabsList>
                            <TabsContent value="alojamientos">
                                <h3 className="text-lg font-semibold mb-2">Alojamientos Consumidos</h3>
                                {servicesLoading && <p>Cargando alojamientos...</p>}
                                {servicesError && (
                                    <Alert variant="destructive">
                                        <AlertCircle className="h-4 w-4" />
                                        <AlertTitle>Error</AlertTitle>
                                        <AlertDescription>Error al cargar alojamientos: {servicesError.message}</AlertDescription>
                                    </Alert>
                                )}
                                {servicesData?.alojamientosByCliente?.map((alojamiento) => (
                                    <Card key={alojamiento.id} className="mb-4">
                                        <CardContent className="p-4">
                                            <Link href={`/servicios/alojamientos/${alojamiento.alojamiento.id}`} className="w-full max-w-sm">
                                                <h4 className="font-semibold">{alojamiento.alojamiento.nombre}</h4>
                                            </Link>
                                            <p>Ubicación: {alojamiento.alojamiento.ubicacion}</p>
                                            <p>Precio por noche: ${alojamiento.alojamiento.precioPorNoche}</p>
                                            <p>Calificación actual: {alojamiento.calificacion}</p>
                                            <div className="mt-2">
                                                <Input
                                                    type="number"
                                                    min="1"
                                                    max="5"
                                                    step="0.1"
                                                    placeholder="Nueva calificación"
                                                    className="mb-2"
                                                    value={ratings[alojamiento.id] || ''}
                                                    onChange={(e) => setRatings(prev => ({ ...prev, [alojamiento.id]: e.target.value }))}
                                                />
                                                <Textarea
                                                    placeholder="Comentario"
                                                    className="mb-2"
                                                    value={comments[alojamiento.id] || ''}
                                                    onChange={(e) => setComments(prev => ({ ...prev, [alojamiento.id]: e.target.value }))}
                                                />
                                                <Button onClick={() => handleRate(alojamiento.id, 'alojamiento')}>
                                                    Enviar Calificación
                                                </Button>
                                            </div>
                                        </CardContent>
                                    </Card>
                                ))}
                            </TabsContent>
                            <TabsContent value="transportes">
                                <h3 className="text-lg font-semibold mb-2">Transportes Consumidos</h3>
                                {servicesLoading && <p>Cargando transportes...</p>}
                                {servicesError && (
                                    <Alert variant="destructive">
                                        <AlertCircle className="h-4 w-4" />
                                        <AlertTitle>Error</AlertTitle>
                                        <AlertDescription>Error al cargar transportes: {servicesError.message}</AlertDescription>
                                    </Alert>
                                )}
                                {servicesData?.transportesByCliente?.map((transporte) => (
                                    <Card key={transporte.id} className="mb-4">
                                        <CardContent className="p-4">
                                            <Link href={`/servicios/transportes/${transporte.transporte.id}`} className="w-full max-w-sm">
                                                <h4 className="font-semibold">{transporte.transporte.origen} - {transporte.transporte.destino}</h4>
                                            </Link>
                                            <p>Número de Placa: {transporte.numeroPlaca}</p>
                                            <p>Precio: ${transporte.transporte.precio}</p>
                                            <p>Calificación actual: {transporte.calificacion}</p>
                                            <div className="mt-2">
                                                <Input
                                                    type="number"
                                                    min="1"
                                                    max="5"
                                                    step="0.1"
                                                    placeholder="Nueva calificación"
                                                    className="mb-2"
                                                    value={ratings[transporte.id] || ''}
                                                    onChange={(e) => setRatings(prev => ({ ...prev, [transporte.transporte.id]: e.target.value }))}
                                                />
                                                <Textarea
                                                    placeholder="Comentario"
                                                    className="mb-2"
                                                    value={comments[transporte.id] || ''}
                                                    onChange={(e) => setComments(prev => ({ ...prev, [transporte.transporte.id]: e.target.value }))}
                                                />
                                                <Button onClick={() => handleRate(transporte.transporte.id, 'transporte')}>
                                                    Enviar Calificación
                                                </Button>
                                            </div>
                                        </CardContent>
                                    </Card>
                                ))}
                            </TabsContent>
                        </Tabs>
                    </div>
                </CardContent>
            </Card>
        </div>
    )
}