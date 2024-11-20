import Image from 'next/image'
import Link from 'next/link'
import { Card, CardHeader, CardTitle, CardContent, CardFooter } from "@/components/ui/card"

const destinations = [
  { name: "Paris, France", description: "Experience the city of love and its iconic landmarks.", image: "https://storage.googleapis.com/cloud-project-javeriana/proveedores/turismo.webp" },
  { name: "Tokyo, Japan", description: "Immerse yourself in the blend of tradition and modernity.", image: "/placeholder.svg?height=400&width=600" },
  { name: "New York, USA", description: "Discover the city that never sleeps and its vibrant culture.", image: "/placeholder.svg?height=400&width=600" },
]

export function DestinationsSection() {
  return (
    <section id="destinations" className="w-full py-12 md:py-24 lg:py-32">
      <div className="container px-4 md:px-6 mx-auto">
        <h2 className="text-3xl font-bold tracking-tighter sm:text-5xl text-center mb-12">Popular Destinations</h2>
        <div className="grid gap-6 sm:grid-cols-2 lg:grid-cols-3 justify-items-center">
          {destinations.map((destination, index) => (
            <Card key={index} className="overflow-hidden w-full max-w-sm">
              <Image
                src={destination.image}
                alt={destination.name}
                width={600}
                height={400}
                className="object-cover w-full h-[200px]"
              />
              <CardHeader>
                <CardTitle>{destination.name}</CardTitle>
              </CardHeader>
              <CardContent>
                <p>{destination.description}</p>
              </CardContent>
              <CardFooter>
                <Link href="#" className="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-10 px-4 py-2">
                  Explore
                </Link>
              </CardFooter>
            </Card>
          ))}
        </div>
      </div>
    </section>
  )
}