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
import Link from 'next/link'

const GET_PROVIDER_PROFILE = gql`
  query GetProviderProfile($correo: String!) {
    proveedorByCorreo(correo: $correo) {
      id
      nombre
      correo
      edad
      foto
      descripcion
      telefono
      pagWeb
      contactoRedes
    }
  }
`

const UPDATE_PROVIDER_PROFILE = gql`
  mutation UpdateProviderProfile($correo: String!, $proveedor: ProveedorInput!) {
    updateProveedorData(correo: $correo, proveedor: $proveedor) {
      foto
      descripcion
    }
  }
`

const GET_PROVIDER_SERVICES = gql`
  query GetProviderServices($correo: String!) {
    alojamientosByProveedor(proveedorCorreo: $correo) {
      id
      nombre
      calificacion
      ubicacion
      precioPorNoche
      descripcion
      latitud
      longitud
    }
    transportesByProveedor(proveedorCorreo: $correo) {
      id
      tipo
      foto
      capacidad
      operador
      precio
      calificacion
      origen
      destino
      fechaSalida
      horaSalida
      duracionEstimada
      latitud
      longitud
      descripcion
    }
  }
`

export default function ProviderProfilePage() {
  const { toast } = useToast()
  const [imageFile, setImageFile] = useState<File | null>(null)
  const [providerEmail, setProviderEmail] = useState('')

  useEffect(() => {
    const userCookie = document.cookie.split('; ').find(row => row.startsWith('user='))
    if (userCookie) {
      const userData = JSON.parse(userCookie.split('=')[1])
      setProviderEmail(userData.email)
    }
  }, [])

  const { data, loading, error } = useQuery(GET_PROVIDER_PROFILE, {
    variables: { correo: providerEmail },
    skip: !providerEmail,
  })

  const { data: servicesData, loading: servicesLoading, error: servicesError } = useQuery(GET_PROVIDER_SERVICES, {
    variables: { correo: providerEmail },
    skip: !providerEmail,
  })

  const [updateProfile] = useMutation(UPDATE_PROVIDER_PROFILE)

  const { register, handleSubmit, setValue } = useForm()

  useEffect(() => {
    if (data?.proveedorByCorreo) {
      setValue('descripcion', data.proveedorByCorreo.descripcion)
      setValue('telefono', data.proveedorByCorreo.telefono)
      setValue('pagWeb', data.proveedorByCorreo.pagWeb)
      setValue('contactoRedes', data.proveedorByCorreo.contactoRedes)
    }
  }, [data, setValue])

  const onSubmit = async (formData) => {
    try {
      let fotoUrl = data?.proveedorByCorreo?.foto

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
          correo: providerEmail,
          proveedor: {
            foto: fotoUrl,
            descripcion: formData.descripcion,
            telefono: formData.telefono,
            pagWeb: formData.pagWeb,
            contactoRedes: formData.contactoRedes,
          },
        },
      })

      toast({ description: "Perfil actualizado con éxito" })
    } catch (error) {
      toast({ variant: "destructive", description: "Error al actualizar el perfil" })
    }
  }

  if (loading) return <p>Cargando perfil...</p>
  if (error) return <p>Error al cargar el perfil: {error.message}</p>
  if (!data || !data.proveedorByCorreo) return <p>No se encontró información del proveedor</p>

  const { nombre, correo, edad, foto, descripcion, telefono, pagWeb, contactoRedes } = data.proveedorByCorreo

  return (
    <div className="container mx-auto p-4">
      <Card>
        <CardHeader>
          <CardTitle>Perfil del Proveedor</CardTitle>
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

              <div>
                <Label htmlFor="telefono">Teléfono</Label>
                <Input
                  id="telefono"
                  {...register('telefono')}
                  defaultValue={telefono}
                />
              </div>

              <div>
                <Label htmlFor="pagWeb">Página Web</Label>
                <Input
                  id="pagWeb"
                  {...register('pagWeb')}
                  defaultValue={pagWeb}
                />
              </div>

              <div>
                <Label htmlFor="contactoRedes">Contacto en Redes Sociales</Label>
                <Input
                  id="contactoRedes"
                  {...register('contactoRedes')}
                  defaultValue={contactoRedes}
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
                <h3 className="text-lg font-semibold mb-2">Alojamientos Ofrecidos</h3>
                {servicesLoading && <p>Cargando alojamientos...</p>}
                {servicesError && <p>Error al cargar alojamientos: {servicesError.message}</p>}
                {servicesData?.alojamientosByProveedor?.map((alojamiento) => (
                  <Card key={alojamiento.id} className="mb-4">
                    <CardContent className="p-4">
                      <Link href={`/servicios/alojamientos/${alojamiento.id}`}>
                        <h4 className="font-semibold">{alojamiento.nombre}</h4>
                      </Link>
                      <p>Ubicación: {alojamiento.ubicacion}</p>
                      <p>Precio por noche: ${alojamiento.precioPorNoche}</p>
                      <p>Calificación: {alojamiento.calificacion}</p>
                    </CardContent>
                  </Card>
                ))}
              </TabsContent>
              <TabsContent value="transportes">
                <h3 className="text-lg font-semibold mb-2">Transportes Ofrecidos</h3>
                {servicesLoading && <p>Cargando transportes...</p>}
                {servicesError && <p>Error al cargar transportes: {servicesError.message}</p>}
                {servicesData?.transportesByProveedor?.map((transporte) => (
                  <Card key={transporte.id} className="mb-4">
                    <CardContent className="p-4">
                      <Link href={`/servicios/transportes/${transporte.id}`}>
                        <h4 className="font-semibold">{transporte.tipo}</h4>
                      </Link>
                      <p>Origen: {transporte.origen}</p>
                      <p>Destino: {transporte.destino}</p>
                      <p>Precio: ${transporte.precio}</p>
                      <p>Calificación: {transporte.calificacion}</p>
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