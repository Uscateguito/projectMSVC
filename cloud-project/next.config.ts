import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  /* config options here */
  images: {
    remotePatterns: [
      {
        protocol: 'https',
        hostname: 'storage.googleapis.com',
        pathname: '/**'
      }
    ]
  }
};

// https://storage.googleapis.com/cloud-project-javeriana/proveedores/turismo.webp

export default nextConfig;
