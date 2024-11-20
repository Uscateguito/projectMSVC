'use client'

import { useState } from 'react'
import { useParams } from 'next/navigation'
import { useQuery, useMutation, gql } from '@apollo/client'
import { useForm } from 'react-hook-form'
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Button } from "@/components/ui/button"
import { Textarea } from "@/components/ui/textarea"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { useToast } from "@/hooks/use-toast"

const GET_ALOJAMIENTOS_BY_PROVEEDOR = gql`
  query GetAlojamientosByProveedor($proveedorCorreo: String!) {
    alojamientosByProveedor(proveedorCorreo: $proveedorCorreo) {
      id
      nombre
      foto
      ubicacion
      calificacion
      precioPorNoche
      descripcion
      latitud
      longitud
    }
  }
`

const GET_TRANSPORTES_BY_PROVEEDOR = gql`
  query GetTransportesByProveedor($proveedorCorreo: String!) {
    transportesByProveedor(proveedorCorreo: $proveedorCorreo) {
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
      descripcion
      latitud
      longitud
    }
  }
`

const ADD_ALOJAMIENTO = gql`
  mutation AddAlojamiento($alojamiento: AlojamientoInput!) {
    addAlojamiento(alojamiento: $alojamiento) {
      id
      nombre
    }
  }
`

const ADD_TRANSPORTE = gql`
  mutation AddTransporte($transporte: TransporteInput!) {
    addTransporte(transporte: $transporte) {
      id
      tipo
    }
  }
`

const UPDATE_ALOJAMIENTO = gql`
  mutation UpdateAlojamiento($id: ID!, $alojamiento: AlojamientoInput!) {
    updateAlojamiento(id: $id, alojamiento: $alojamiento) {
      id
      nombre
    }
  }
`

const UPDATE_TRANSPORTE = gql`
  mutation UpdateTransporte($id: ID!, $transporte: TransporteInput!) {
    updateTransporte(id: $id, transporte: $transporte) {
      id
      tipo
    }
  }
`

const DELETE_ALOJAMIENTO = gql`
  mutation DeleteAlojamiento($id: ID!) {
    deleteAlojamiento(id: $id)
  }
`

const DELETE_TRANSPORTE = gql`
  mutation DeleteTransporte($id: ID!) {
    deleteTransporte(id: $id)
  }
`

export default function ProviderPage() {
  const [activeTab, setActiveTab] = useState('alojamientos')
  const [editingId, setEditingId] = useState<string | null>(null)
  const { toast } = useToast()
  const params = useParams()
  const proveedorCorreo = decodeURIComponent(params.id as string)

  const { data: alojamientosData, refetch: refetchAlojamientos } = useQuery(GET_ALOJAMIENTOS_BY_PROVEEDOR, {
    variables: { proveedorCorreo },
    skip: !proveedorCorreo
  })
  const { data: transportesData, refetch: refetchTransportes } = useQuery(GET_TRANSPORTES_BY_PROVEEDOR, {
    variables: { proveedorCorreo },
    skip: !proveedorCorreo
  })

  const [addAlojamiento] = useMutation(ADD_ALOJAMIENTO)
  const [addTransporte] = useMutation(ADD_TRANSPORTE)
  const [updateAlojamiento] = useMutation(UPDATE_ALOJAMIENTO)
  const [updateTransporte] = useMutation(UPDATE_TRANSPORTE)
  const [deleteAlojamiento] = useMutation(DELETE_ALOJAMIENTO)
  const [deleteTransporte] = useMutation(DELETE_TRANSPORTE)

  const { register: registerAlojamiento, handleSubmit: handleSubmitAlojamiento, reset: resetAlojamiento } = useForm()
  const { register: registerTransporte, handleSubmit: handleSubmitTransporte, reset: resetTransporte } = useForm()

  const onSubmitAlojamiento = async (data) => {
    try {
      const alojamientoInput = { ...data, proveedorCorreo }
      if (editingId) {
        await updateAlojamiento({ variables: { id: editingId, alojamiento: alojamientoInput } })
        toast({ description: "Alojamiento actualizado con éxito" })
      } else {
        await addAlojamiento({ variables: { alojamiento: alojamientoInput } })
        toast({ description: "Alojamiento agregado con éxito" })
      }
      refetchAlojamientos()
      resetAlojamiento()
      setEditingId(null)
    } catch (error) {
      toast({ variant: "destructive", description: error.message })
    }
  }

  const onSubmitTransporte = async (data) => {
    try {
      const transporteInput = { ...data, proveedorCorreo }
      if (editingId) {
        await updateTransporte({ variables: { id: editingId, transporte: transporteInput } })
        toast({ description: "Transporte actualizado con éxito" })
      } else {
        await addTransporte({ variables: { transporte: transporteInput } })
        toast({ description: "Transporte agregado con éxito" })
      }
      refetchTransportes()
      resetTransporte()
      setEditingId(null)
    } catch (error) {
      toast({ variant: "destructive", description: error.message })
    }
  }

  const handleDelete = async (id, type) => {
    try {
      if (type === 'alojamiento') {
        await deleteAlojamiento({ variables: { id } })
        refetchAlojamientos()
      } else {
        await deleteTransporte({ variables: { id } })
        refetchTransportes()
      }
      toast({ description: `${type === 'alojamiento' ? 'Alojamiento' : 'Transporte'} eliminado con éxito` })
    } catch (error) {
      toast({ variant: "destructive", description: error.message })
    }
  }

  const handleEdit = (id, type, data) => {
    setEditingId(id)
    if (type === 'alojamiento') {
      resetAlojamiento(data)
    } else {
      resetTransporte(data)
    }
    setActiveTab(type === 'alojamiento' ? 'alojamientos' : 'transportes')
  }

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-2xl font-bold mb-4">Panel de Proveedor</h1>
      <p className="mb-4">Correo del Proveedor: {proveedorCorreo}</p>
      <Tabs value={activeTab} onValueChange={setActiveTab}>
        <TabsList>
          <TabsTrigger value="alojamientos">Alojamientos</TabsTrigger>
          <TabsTrigger value="transportes">Transportes</TabsTrigger>
        </TabsList>
        <TabsContent value="alojamientos">
          <Card>
            <CardHeader>
              <CardTitle>{editingId ? 'Editar Alojamiento' : 'Agregar Alojamiento'}</CardTitle>
            </CardHeader>
            <CardContent>
              <form onSubmit={handleSubmitAlojamiento(onSubmitAlojamiento)} className="space-y-4">
                <div className="grid grid-cols-2 gap-4">
                  <div className="space-y-2">
                    <Label htmlFor="nombre">Nombre</Label>
                    <Input id="nombre" {...registerAlojamiento('nombre', { required: true })} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="foto">URL de la foto</Label>
                    <Input id="foto" {...registerAlojamiento('foto')} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="ubicacion">Ubicación</Label>
                    <Input id="ubicacion" {...registerAlojamiento('ubicacion', { required: true })} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="calificacion">Calificación</Label>
                    <Input id="calificacion" type="number" step="0.1" {...registerAlojamiento('calificacion', { required: true, min: 0, max: 5 })} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="precioPorNoche">Precio por noche</Label>
                    <Input id="precioPorNoche" type="number" step="0.01" {...registerAlojamiento('precioPorNoche', { required: true, min: 0 })} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="latitud">Latitud</Label>
                    <Input id="latitud" type="number" step="any" {...registerAlojamiento('latitud', { required: true })} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="longitud">Longitud</Label>
                    <Input id="longitud" type="number" step="any" {...registerAlojamiento('longitud', { required: true })} />
                  </div>
                </div>
                <div className="space-y-2">
                  <Label htmlFor="descripcion">Descripción</Label>
                  <Textarea id="descripcion" {...registerAlojamiento('descripcion', { required: true })} />
                </div>
                <Button type="submit">{editingId ? 'Actualizar' : 'Agregar'} Alojamiento</Button>
              </form>
            </CardContent>
          </Card>
          <Card className="mt-4">
            <CardHeader>
              <CardTitle>Lista de Alojamientos</CardTitle>
            </CardHeader>
            <CardContent>
              <Table>
                <TableHeader>
                  <TableRow>
                    <TableHead>Nombre</TableHead>
                    <TableHead>Ubicación</TableHead>
                    <TableHead>Precio por Noche</TableHead>
                    <TableHead>Acciones</TableHead>
                  </TableRow>
                </TableHeader>
                <TableBody>
                  {alojamientosData?.alojamientosByProveedor.map((alojamiento) => (
                    <TableRow key={alojamiento.id}>
                      <TableCell>{alojamiento.nombre}</TableCell>
                      <TableCell>{alojamiento.ubicacion}</TableCell>
                      <TableCell>${alojamiento.precioPorNoche}</TableCell>
                      <TableCell>
                        <Button variant="outline" className="mr-2" onClick={() => handleEdit(alojamiento.id, 'alojamiento', alojamiento)}>Editar</Button>
                        <Button variant="destructive" onClick={() => handleDelete(alojamiento.id, 'alojamiento')}>Eliminar</Button>
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </CardContent>
          </Card>
        </TabsContent>
        <TabsContent value="transportes">
          <Card>
            <CardHeader>
              <CardTitle>{editingId ? 'Editar Transporte' : 'Agregar Transporte'}</CardTitle>
            </CardHeader>
            <CardContent>
              <form onSubmit={handleSubmitTransporte(onSubmitTransporte)} className="space-y-4">
                <div className="grid grid-cols-2 gap-4">
                  <div className="space-y-2">
                    <Label htmlFor="tipo">Tipo</Label>
                    <Input id="tipo" {...registerTransporte('tipo', { required: true })} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="foto">URL de la foto</Label>
                    <Input id="foto" {...registerTransporte('foto')} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="capacidad">Capacidad</Label>
                    <Input id="capacidad" type="number" {...registerTransporte('capacidad', { required: true, min: 1 })} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="operador">Operador</Label>
                    <Input id="operador" {...registerTransporte('operador', { required: true })} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="precio">Precio</Label>
                    <Input id="precio" type="number" step="0.01" {...registerTransporte('precio', { required: true, min: 0 })} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="calificacion">Calificación</Label>
                    <Input id="calificacion" type="number" step="0.1" {...registerTransporte('calificacion', { required: true, min: 0, max: 5 })} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="origen">Origen</Label>
                    <Input id="origen" {...registerTransporte('origen', { required: true })} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="destino">Destino</Label>
                    <Input id="destino" {...registerTransporte('destino', { required: true })} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="fechaSalida">Fecha de Salida</Label>
                    <Input id="fechaSalida" type="date" {...registerTransporte('fechaSalida', { required: true })} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="horaSalida">Hora de Salida</Label>
                    <Input id="horaSalida" type="time" {...registerTransporte('horaSalida', { required: true })} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="duracionEstimada">Duración Estimada (minutos)</Label>
                    <Input id="duracionEstimada" type="number" {...registerTransporte('duracionEstimada', { required: true, min: 1 })} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="latitud">Latitud</Label>
                    <Input id="latitud" type="number" step="any" {...registerTransporte('latitud', { required: true })} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="longitud">Longitud</Label>
                    <Input id="longitud" type="number" step="any" {...registerTransporte('longitud', { required: true })} />
                  </div>
                </div>
                <div className="space-y-2">
                  <Label htmlFor="descripcion">Descripción</Label>
                  <Textarea id="descripcion" {...registerTransporte('descripcion', { required: true })} />
                </div>
                <Button type="submit">{editingId ? 'Actualizar' : 'Agregar'} Transporte</Button>
              </form>
            </CardContent>
          </Card>
          <Card className="mt-4">
            <CardHeader>
              <CardTitle>Lista de Transportes</CardTitle>
            </CardHeader>
            <CardContent>
              <Table>
                <TableHeader>
                  <TableRow>
                    <TableHead>Tipo</TableHead>
                    <TableHead>Origen</TableHead>
                    <TableHead>Destino</TableHead>
                    <TableHead>Precio</TableHead>
                    <TableHead>Acciones</TableHead>
                  </TableRow>
                </TableHeader>
                <TableBody>
                  {transportesData?.transportesByProveedor.map((transporte) => (
                    <TableRow key={transporte.id}>
                      <TableCell>{transporte.tipo}</TableCell>
                      <TableCell>{transporte.origen}</TableCell>
                      <TableCell>{transporte.destino}</TableCell>
                      <TableCell>${transporte.precio}</TableCell>
                      <TableCell>
                        <Button variant="outline" className="mr-2" onClick={() => handleEdit(transporte.id, 'transporte', transporte)}>Editar</Button>
                        <Button variant="destructive" onClick={() => handleDelete(transporte.id, 'transporte')}>Eliminar</Button>
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </CardContent>
          </Card>
        </TabsContent>
      </Tabs>
    </div>
  )
}