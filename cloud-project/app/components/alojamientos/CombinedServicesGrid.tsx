'use client';

import { useState } from 'react';
import { useQuery, gql } from "@apollo/client";
import Image from 'next/image';
import Link from 'next/link';
import { Card, CardContent, CardDescription, CardFooter, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Button } from "@/components/ui/button"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { Star, ChevronLeft, ChevronRight, Home, Truck } from 'lucide-react';
import { Alojamiento, Transporte } from "@/app/lib/definitions";

const GET_ALOJAMIENTOS = gql`
  query {
    alojamientos {
      id
      nombre
      foto
      calificacion
      ubicacion
      precioPorNoche
      descripcion
      latitud
      longitud
    }
  }
`;

const GET_TRANSPORTES = gql`
  query {
    transportes {
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
    }
  }
`;

const ITEMS_PER_PAGE = 10;
const DEFAULT_IMAGE = "https://storage.googleapis.com/cloud-project-javeriana/proveedores/turismo.webp";

type ServiceType = 'all' | 'alojamiento' | 'transporte';

export function CombinedServicesGrid() {
  const [searchTerm, setSearchTerm] = useState('');
  const [currentPage, setCurrentPage] = useState(1);
  const [serviceType, setServiceType] = useState<ServiceType>('all');

  const { loading: loadingAlojamientos, error: errorAlojamientos, data: dataAlojamientos } = useQuery(GET_ALOJAMIENTOS);
  const { loading: loadingTransportes, error: errorTransportes, data: dataTransportes } = useQuery(GET_TRANSPORTES);

  if (loadingAlojamientos || loadingTransportes) return <p>Cargando servicios...</p>;
  if (errorAlojamientos || errorTransportes) return <p>Error al cargar los servicios: {errorAlojamientos?.message || errorTransportes?.message}</p>;

  const alojamientos = dataAlojamientos.alojamientos || [];
  const transportes = dataTransportes.transportes || [];

  const filteredServices = [...alojamientos, ...transportes].filter((service) => {
    const matchesSearch = service.nombre?.toLowerCase().includes(searchTerm.toLowerCase()) ||
                          service.id.toLowerCase().includes(searchTerm.toLowerCase()) ||
                          service.tipo?.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesType = serviceType === 'all' || 
                        (serviceType === 'alojamiento' && 'nombre' in service) ||
                        (serviceType === 'transporte' && 'tipo' in service);
    return matchesSearch && matchesType;
  });

  const totalPages = Math.ceil(filteredServices.length / ITEMS_PER_PAGE);
  const startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
  const endIndex = startIndex + ITEMS_PER_PAGE;
  const currentServices = filteredServices.slice(startIndex, endIndex);

  const goToNextPage = () => setCurrentPage((page) => Math.min(page + 1, totalPages));
  const goToPreviousPage = () => setCurrentPage((page) => Math.max(page - 1, 1));

  const isValidImageUrl = (url: string) => url && url.startsWith('https://storage.googleapis.com/');

  const renderServiceCard = (service: Alojamiento | Transporte) => {
    const isAlojamiento = 'nombre' in service;
    return (
      <Link href={isAlojamiento ? `/servicios/alojamientos/${service.id}` : `/servicios/transportes/${service.id}`} key={service.id} className="w-full max-w-sm">
        <Card className="w-full max-w-sm shadow-none hover:shadow-md transition-shadow duration-300">
          <Image
            src={isValidImageUrl(service.foto) ? service.foto : DEFAULT_IMAGE}
            alt={isAlojamiento ? service.nombre : service.tipo}
            width={300}
            height={200}
            className="w-full h-72 object-cover rounded"
          />
          <CardContent className="mt-4">
            <CardTitle className="text-lg font-semibold">
              {isAlojamiento ? (
                <><Home className="inline-block mr-2" />{service.nombre}</>
              ) : (
                <><Truck className="inline-block mr-2" />{service.tipo}</>
              )}
            </CardTitle>
            <CardDescription className="text-sm text-gray-500">
              {isAlojamiento ? service.ubicacion : `${service.origen} - ${service.destino}`}
            </CardDescription>
            <div className="flex items-center mt-2">
              <Star className="w-5 h-5 text-yellow-400 mr-1" />
              <span className="text-sm">{service.calificacion.toFixed(1)}</span>
            </div>
            <p className="mt-2 text-sm text-gray-600 line-clamp-2">
              {isAlojamiento ? service.descripcion : `Operador: ${service.operador}`}
            </p>
          </CardContent>
          <CardFooter>
            <p className="text-lg font-bold">
              ${isAlojamiento ? `${service.precioPorNoche} por noche` : service.precio}
            </p>
          </CardFooter>
        </Card>
      </Link>
    );
  };

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="mb-6 flex flex-col sm:flex-row gap-4 items-center justify-center">
        <Input
          type="text"
          placeholder="Buscar por nombre, ID o tipo..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="w-full max-w-md"
        />
        <Select value={serviceType} onValueChange={(value: ServiceType) => setServiceType(value)}>
          <SelectTrigger className="w-full max-w-[200px]">
            <SelectValue placeholder="Tipo de servicio" />
          </SelectTrigger>
          <SelectContent>
            <SelectItem value="all">Todos</SelectItem>
            <SelectItem value="alojamiento">Alojamientos</SelectItem>
            <SelectItem value="transporte">Transportes</SelectItem>
          </SelectContent>
        </Select>
      </div>
      <div className="grid gap-x-3 gap-y-6 sm:grid-cols-2 md:grid-cols-5 justify-items-center">
        {currentServices.map(renderServiceCard)}
      </div>
      {filteredServices.length === 0 && (
        <p className="text-center mt-6 text-gray-500">No se encontraron servicios que coincidan con la búsqueda.</p>
      )}
      {filteredServices.length > ITEMS_PER_PAGE && (
        <div className="flex justify-center items-center mt-8 space-x-4">
          <Button onClick={goToPreviousPage} disabled={currentPage === 1} variant="outline">
            <ChevronLeft className="h-4 w-4 mr-2" />
            Anterior
          </Button>
          <span className="text-sm text-gray-600">
            Página {currentPage} de {totalPages}
          </span>
          <Button onClick={goToNextPage} disabled={currentPage === totalPages} variant="outline">
            Siguiente
            <ChevronRight className="h-4 w-4 ml-2" />
          </Button>
        </div>
      )}
    </div>
  );
}