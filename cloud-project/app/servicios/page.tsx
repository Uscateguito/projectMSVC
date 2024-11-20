import { Header } from "@/app/components/Header";
import { CombinedServicesGrid } from "../components/alojamientos/CombinedServicesGrid";

export default function ServicesPage() {
  return (
    <div className="flex flex-col min-h-screen">
      <CombinedServicesGrid />
    </div>
  );
}