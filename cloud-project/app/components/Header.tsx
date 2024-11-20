import Link from 'next/link';
import { Plane } from 'lucide-react';

export function Header() {
  return (
    <header className="px-4 lg:px-6 h-14 flex items-center bg-gray-900 text-white">
      <Link className="flex items-center justify-center" href="/">
        <Plane className="h-6 w-6 text-white" />
        <span className="sr-only">Acme Tourism</span>
      </Link>
      <nav className="ml-auto flex gap-4 sm:gap-6">
        <Link
          className="text-sm font-medium hover:underline underline-offset-4 hover:text-gray-300"
          href="/servicios"
        >
          Services
        </Link>
        <Link
          className="text-sm font-medium hover:underline underline-offset-4 hover:text-gray-300"
          href="#about"
        >
          About
        </Link>
        <Link
          className="text-sm font-medium hover:underline underline-offset-4 hover:text-gray-300"
          href="#contact"
        >
          Contact
        </Link>
      </nav>
    </header>
  );
}
