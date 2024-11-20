'use client'

import { useState } from 'react'
import { useRouter } from 'next/navigation'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Button } from "@/components/ui/button"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import { Switch } from "@/components/ui/switch"
import { useToast } from "@/hooks/use-toast"

export default function AuthPage() {
  const [isClient, setIsClient] = useState(true)
  const [isLogin, setIsLogin] = useState(true)
  const router = useRouter()
  const { toast } = useToast()

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault()
    const formData = new FormData(event.currentTarget)
    const username = formData.get('username') as string
    const password = formData.get('password') as string
    const email = formData.get('email') as string
    const firstName = formData.get('firstName') as string
    const lastName = formData.get('lastName') as string

    const endpoint = isClient
      ? isLogin
        ? 'http://localhost:8080/api/client/auth/login'
        : 'http://localhost:8080/api/client/auth/register'
      : isLogin
        ? 'http://localhost:8080/api/provider/auth/login'
        : 'http://localhost:8080/api/provider/auth/register'

    try {
      const response = isLogin
        ? await fetch(`${endpoint}?username=${username}&password=${password}`,
          {
            method: 'POST'
          }
        )
        : await fetch(endpoint, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username, password, email, firstName, lastName }),
          })

      if (response.status === 200 || response.status === 201) {
        // Store user data from the form in cookies
        const userData = {
          username,
          email: email || username, // Use username as email for login
          firstName: firstName || '',
          lastName: lastName || '',
          role: isClient ? 'client' : 'provider'
        }
        document.cookie = `user=${JSON.stringify(userData)}; path=/`
        toast({ description: isLogin ? "Logged in successfully" : "Registered successfully" })
        router.push(isClient ? '/client/profile' : '/provider/profile')
      } else {
        toast({ variant: "destructive", description: "Authentication failed" })
      }
    } catch (error) {
      toast({ variant: "destructive", description: "An error occurred" })
    }
  }

  return (
    <div className="container mx-auto p-4">
      <Card className="max-w-md mx-auto">
        <CardHeader>
          <CardTitle>{isLogin ? 'Login' : 'Sign Up'}</CardTitle>
          <CardDescription>
            {isLogin ? 'Enter your credentials to log in' : 'Create a new account'}
          </CardDescription>
        </CardHeader>
        <CardContent>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="flex items-center space-x-2">
              <Switch
                id="user-type"
                checked={isClient}
                onCheckedChange={setIsClient}
              />
              <Label htmlFor="user-type">{isClient ? 'Client' : 'Provider'}</Label>
            </div>
            <Tabs value={isLogin ? 'login' : 'signup'} onValueChange={(value) => setIsLogin(value === 'login')}>
              <TabsList className="grid w-full grid-cols-2">
                <TabsTrigger value="login">Login</TabsTrigger>
                <TabsTrigger value="signup">Sign Up</TabsTrigger>
              </TabsList>
              <TabsContent value="login">
                <div className="space-y-2">
                  <Label htmlFor="username">Username</Label>
                  <Input id="username" name="username" required />
                </div>
                <div className="space-y-2">
                  <Label htmlFor="password">Password</Label>
                  <Input id="password" name="password" type="password" required />
                </div>
              </TabsContent>
              <TabsContent value="signup">
                <div className="space-y-2">
                  <Label htmlFor="username">Username</Label>
                  <Input id="username" name="username" required />
                </div>
                <div className="space-y-2">
                  <Label htmlFor="email">Email</Label>
                  <Input id="email" name="email" type="email" required />
                </div>
                <div className="space-y-2">
                  <Label htmlFor="firstName">First Name</Label>
                  <Input id="firstName" name="firstName" required />
                </div>
                <div className="space-y-2">
                  <Label htmlFor="lastName">Last Name</Label>
                  <Input id="lastName" name="lastName" required />
                </div>
                <div className="space-y-2">
                  <Label htmlFor="password">Password</Label>
                  <Input id="password" name="password" type="password" required />
                </div>
              </TabsContent>
            </Tabs>
            <Button type="submit" className="w-full">
              {isLogin ? 'Login' : 'Sign Up'}
            </Button>
          </form>
        </CardContent>
      </Card>
    </div>
  )
}