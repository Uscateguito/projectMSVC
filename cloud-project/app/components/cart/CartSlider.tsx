'use client';

import { useState } from 'react';
import { gql, useMutation } from '@apollo/client';

// Define la query rentAlojamiento
const RENT_ALOJAMIENTO = gql`
  mutation RentAlojamiento(
    $personaCorreo: String!
    $alojamientoId: ID!
    $fechaCheckIn: String!
    $fechaCheckOut: String!
  ) {
    rentAlojamiento(
      personaCorreo: $personaCorreo
      alojamientoId: $alojamientoId
      fechaCheckIn: $fechaCheckIn
      fechaCheckOut: $fechaCheckOut
    ) {
      id
      personaCorreo
      alojamientoId
      fechaCheckIn
      fechaCheckOut
    }
  }
`;

interface CartSliderProps {
  isOpen: boolean;
  onClose: () => void;
}

export default function CartSlider({ isOpen, onClose }: CartSliderProps) {
  const [rentAlojamiento] = useMutation(RENT_ALOJAMIENTO);
  const [loading, setLoading] = useState(false);

  // Función para ejecutar la compra
  const handlePurchase = async () => {
    setLoading(true);
    try {
      await rentAlojamiento({
        variables: {
          personaCorreo: "usuario@ejemplo.com",
          alojamientoId: "123",
          fechaCheckIn: "2023-12-01",
          fechaCheckOut: "2023-12-07",
        },
      });
      alert("Compra realizada con éxito");
    } catch (error) {
      console.error("Error en la compra:", error);
      alert("Error en la compra");
    } finally {
      setLoading(false);
      onClose(); // Cierra el carrito después de la compra
    }
  };

  return (
    <div
      className={`fixed top-0 right-0 h-full w-80 bg-white shadow-lg transform transition-transform ${
        isOpen ? "translate-x-0" : "translate-x-full"
      }`}
    >
      <div className="p-4">
        <h2 className="text-xl font-semibold">Carrito de Compras</h2>
        <p className="mt-2">Detalle del alojamiento...</p>
        <button
          onClick={handlePurchase}
          className="mt-4 bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
          disabled={loading}
        >
          {loading ? "Comprando..." : "Comprar"}
        </button>
        <button
          onClick={onClose}
          className="mt-4 bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
        >
          Cerrar
        </button>
      </div>
    </div>
  );
}
