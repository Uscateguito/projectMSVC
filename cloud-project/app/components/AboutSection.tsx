import Image from 'next/image'

export function AboutSection() {
  return (
    <section id="about" className="w-full py-12 md:py-24 lg:py-32 bg-gray-100 dark:bg-gray-800">
      <div className="container px-4 md:px-6 mx-auto">
        <div className="grid gap-6 lg:grid-cols-2 lg:gap-12 items-center justify-items-center">
          <div className="space-y-4 text-center">
            <h2 className="text-3xl font-bold tracking-tighter sm:text-5xl">About Us</h2>
            <p className="text-gray-500 dark:text-gray-400">
              At Acme Tourism, we&apos;re passionate about creating unforgettable travel experiences. With over 20 years in the
              industry, we&apos;ve helped thousands of travelers explore the world, discover new cultures, and create lasting
              memories.
            </p>
            <p className="text-gray-500 dark:text-gray-400">
              Our team of expert travel consultants is dedicated to crafting personalized itineraries that cater to your
              unique interests and preferences. Whether you&apos;re seeking adventure, relaxation, or cultural immersion, we&apos;re
              here to make your travel dreams a reality.
            </p>
          </div>
          <div className="flex justify-center lg:justify-end">
            <Image
              src="/turismo.webp"
              alt="About Us"
              width={1000}
              height={561}
              className="rounded-lg object-cover"
            />
          </div>
        </div>
      </div>
    </section>
  )
}