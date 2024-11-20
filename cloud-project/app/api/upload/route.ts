import { NextRequest, NextResponse } from 'next/server'
import { Storage } from '@google-cloud/storage'

const storage = new Storage({
  projectId: process.env.GOOGLE_CLOUD_PROJECT_ID,
  credentials: JSON.parse(process.env.GOOGLE_CLOUD_CREDENTIALS || '{}'),
})

// console.log('GOOGLE_CLOUD_PROJECT_ID:', process.env.GOOGLE_CLOUD_PROJECT_ID)
// console.log('GOOGLE_CLOUD_CREDENTIALS:',JSON.parse(process.env.GOOGLE_CLOUD_CREDENTIALS || '{}'))
// console.log('GOOGLE_CLOUD_STORAGE_BUCKET:', process.env.GOOGLE_CLOUD_STORAGE_BUCKET)
const bucket = storage.bucket(process.env.GOOGLE_CLOUD_STORAGE_BUCKET || '')

export async function POST(request: NextRequest) {
  try {
    const formData = await request.formData()
    const file = formData.get('file') as File

    if (!file) {
      return NextResponse.json({ error: 'No file uploaded' }, { status: 400 })
    }

    const buffer = await file.arrayBuffer()
    const fileName = `${Date.now()}-${file.name}`
    const fileUpload = bucket.file(fileName)

    await fileUpload.save(Buffer.from(buffer), {
      metadata: {
        contentType: file.type,
      },
    })

    const [url] = await fileUpload.getSignedUrl({
      action: 'read',
      expires: '03-01-2500',
    })

    return NextResponse.json({ url })
  } catch (error) {
    console.error('Error uploading file:', error)
    return NextResponse.json({ error: 'Error uploading file' }, { status: 500 })
  }
}