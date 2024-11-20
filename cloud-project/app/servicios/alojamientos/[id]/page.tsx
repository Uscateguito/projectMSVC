'use client'

import { useEffect, useState } from 'react'
import { useParams } from 'next/navigation'
import { useQuery } from '@apollo/client'
import { gql } from '@apollo/client'
import { Button } from '@/components/ui/button'
import { GoogleMap, LoadScript, Marker } from '@react-google-maps/api'

import Image from 'next/image'
import { Alojamiento } from '@/app/lib/definitions'
import { Header } from '@/app/components/Header'

const GET_ALOJAMIENTO = gql`
  query Alojamiento($id: ID!) {
    alojamientoById(id: $id){
    id
    nombre
    foto
    calificacion
    ubicacion
    precioPorNoche
    latitud
    longitud
    descripcion
  }
}
`

export default function AlojamientoDetails() {
    const { id } = useParams()
    const { loading, error, data } = useQuery(GET_ALOJAMIENTO, {
        variables: { id },
    })

    const [countryInfo, setCountryInfo] = useState(null)
    const [weatherInfo, setWeatherInfo] = useState(null)

    //   console.log("esta es la data traida", data.alojamientoById);
    useEffect(() => {
        if (data?.alojamientoById) {
            // Fetch country info
            fetch(`https://restcountries.com/v3.1/alpha/US`)
                .then(res => res.json())
                .then(data => setCountryInfo(data[0]))

            // Fetch weather info
            fetch(`https://api.openweathermap.org/data/2.5/weather?lat=${data.alojamientoById.latitud}&lon=${data.alojamientoById.longitud}&appid=affcec94ca76ffea200be619ef387b44`)
                .then(res => res.json())
                .then(data => setWeatherInfo(data))
        }
    }, [data])

    if (loading) return <p>Loading...</p>
    if (error) return <p>Error: {error.message}</p>

    const alojamiento: Alojamiento = data.alojamientoById

    return (
        <div className="flex flex-col min-h-screen">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-8 mt-10 ml-20 mr-20">
                <div className='md:col-start-1'>
                    <Image
                        src={alojamiento.foto}
                        alt={alojamiento.nombre}
                        width={300}
                        height={200}
                        className="w-full object-cover rounded-lg" />
                </div>

                <div className='row-start-3 md:col-start-1 md:col-span-2'>
                    {alojamiento && (
                        <div className="mt-8 col-start-1">
                            <LoadScript googleMapsApiKey="AIzaSyBqfVDHnbZpFTQqgLCiRo_LhpUUZBDtXtI">
                                <GoogleMap
                                    mapContainerStyle={{ width: '100%', height: '300px' }}
                                    center={{ lat: alojamiento.latitud, lng: alojamiento.longitud }}
                                    zoom={10}
                                    mapContainerClassName='rounded-lg shadow-md'
                                >
                                    <Marker position={{ lat: alojamiento.latitud, lng: alojamiento.longitud }} />
                                </GoogleMap>
                            </LoadScript>
                        </div>
                    )}
                </div>

                <div className='relative md:col-start-2 md:row-start-1'>
                    <div className='flex flex-col justify-center'>
                        <div>
                            <h1 className="text-3xl font-bold mb-4">{alojamiento.nombre}</h1>
                            <p className="text-gray-600 mb-4">{alojamiento.descripcion}</p>
                            <p className="text-2xl font-bold mb-6">${alojamiento.precioPorNoche}</p>
                            <Button>Add to Cart</Button>
                        </div>

                        <div className="flex gap-12">
                            {countryInfo && (
                                <div className="mt-8">
                                    <h2 className="text-xl font-semibold mb-4">Country Information</h2>
                                    <p>Country: {countryInfo.name.common}</p>
                                    <p>Capital: {countryInfo.capital[0]}</p>
                                    <p>Population: {countryInfo.population.toLocaleString()}</p>
                                </div>
                            )}

                            {weatherInfo && (
                                <div className="mt-8">
                                    <h2 className="text-xl font-semibold mb-4">Weather Information</h2>
                                    <p>Temperature: {(weatherInfo.main.temp - 273.15).toFixed(2)}°C</p>
                                    <p>Humidity: {weatherInfo.main.humidity}%</p>
                                    <p>Weather: {weatherInfo.weather[0].description}</p>
                                </div>
                            )}
                        </div>
                    </div>

                </div>
            </div>
        </div>
    )
}