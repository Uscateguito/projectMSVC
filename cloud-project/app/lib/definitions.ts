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

export class Transporte_Persona {
    id: string
    numeroPlaca: string
    comentario: string
    calificacion: number
    cliente: {
        nombre: string
    }
}

export class Alojamiento_Persona {
    id: string
    fechaCheckIn: string
    fechaCheckOut: string
    comentario: string
    calificacion: number
    cliente: {
        nombre: string
    }
}