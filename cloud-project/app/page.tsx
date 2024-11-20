import { Header } from '@/app/components/Header'
import { HeroSection } from '@/app/components/HeroSection'
import { ServicesSection } from '@/app/components/ServicesSection'
import { AboutSection } from '@/app/components/AboutSection'
import { ContactForm } from '@/app/components/ContactForm'
import { Footer } from '@/app/components/Footer'

export default function Home() {
  return (
    <div className="flex flex-col min-h-screen">
      <Header />
      <main className="flex-1">
        <HeroSection />
        <ServicesSection />
        <AboutSection />
        <ContactForm />
      </main>
      <Footer />
    </div>
  )
}
