import { Card, CardHeader, CardTitle, CardDescription } from "@/components/ui/card"
import { Hotel, Bus } from 'lucide-react'

const services = [
  { icon: Hotel, title: "Accommodation", description: "Find the perfect place to stay, from luxury hotels to cozy B&Bs." },
  { icon: Bus, title: "Transportation", description: "Book transport to any destination at competitive prices." },
  // { icon: Map, title: "Tours & Activities", description: "Explore local culture and attractions with our guided tours." },
  // { icon: Utensils, title: "Culinary Experiences", description: "Savor local cuisines and enjoy food tours in various destinations." },
  // { icon: Camera, title: "Photography Tours", description: "Capture stunning landscapes and moments with our photography tours." },
  // { icon: Users, title: "Group Travel", description: "Join group tours or organize your own for a shared travel experience." },
]

export function ServicesSection() {
  return (
    <section id="services" className="w-full py-12 md:py-24 lg:py-32 bg-gray-100 dark:bg-gray-800">
      <div className="container px-4 md:px-6 mx-auto">
        <h2 className="text-3xl font-bold tracking-tighter sm:text-5xl text-center mb-12">Our Services</h2>
        <div className="grid gap-6 sm:grid-cols-2 lg:grid-cols-1 lg:grid-rows-1 justify-items-center">
          {services.map((service, index) => (
            <Card key={index} className="w-full max-w-sm">
              <CardHeader>
                <div className="flex items-center space-x-2">
                  <service.icon className="w-6 h-6" />
                  <CardTitle>{service.title}</CardTitle>
                </div>
                <CardDescription>{service.description}</CardDescription>
              </CardHeader>
            </Card>
          ))}
        </div>
      </div>
    </section>
  )
}