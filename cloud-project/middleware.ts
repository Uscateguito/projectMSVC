import { NextResponse } from 'next/server'
import type { NextRequest } from 'next/server'

export function middleware(request: NextRequest) {
  const userCookie = request.cookies.get('user')
  const user = userCookie ? JSON.parse(userCookie.value) : null

  if (request.nextUrl.pathname.startsWith('/provider') && user?.role !== 'provider') {
    return NextResponse.redirect(new URL('/auth', request.url))
  }

  return NextResponse.next()
}

export const config = {
  matcher: '/provider/:path*',
}