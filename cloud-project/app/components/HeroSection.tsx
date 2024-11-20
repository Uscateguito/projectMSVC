import Link from 'next/link'
import { Button } from "@/components/ui/button"

export function HeroSection() {
  return (
    <section className="w-full py-12 md:py-24 lg:py-32 xl:py-48 bg-black flex items-center justify-center">
      <div className="container px-4 md:px-6 text-center">
        <div className="flex flex-col items-center space-y-4 text-center">
          <div className="space-y-2">
            <h1 className="text-3xl font-bold tracking-tighter sm:text-4xl md:text-5xl lg:text-6xl/none text-white">
              Discover the World with Us
            </h1>
            <p className="mx-auto max-w-[700px] text-gray-300 md:text-xl">
              Embark on unforgettable journeys, create lasting memories, and explore the beauty of our planet.
            </p>
          </div>
          <div className="space-x-4">
            <Button asChild>
              <Link href="#contact">Book Now</Link>
            </Button>
            <Button asChild variant="outline">
              <Link href="#services">Our Services</Link>
            </Button>
          </div>
        </div>
      </div>
    </section>
  )
}