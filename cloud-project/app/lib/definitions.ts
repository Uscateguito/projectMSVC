export type Alojamiento = {
    id: string
    nombre: string
    foto: string
    calificacion: number
    ubicacion: string
    precioPorNoche: number
    descripcion: string
    latitud: number
    longitud: number
}

export type Transporte = {
    id: string
    tipo: string
    foto: string
    capacidad: number
    operador: string
    precio: number
    calificacion: number
    origen: string
    destino: string
    fechaSalida: string
    horaSalida: string
    duracionEstimada: string
    descripcion: string
    latitud: number
    longitud: number
}