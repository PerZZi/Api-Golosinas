import { useState } from 'react'
import Login from './componentes/Login'
import Navbar from './componentes/Navbar'
import Productos from './componentes/Productos'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className='flex flex-col min-h-screen'>

    <header className='flex justify-between items-center bg-morado h-20'>
      <div className='flex'>
      <img className='w-[140px] h-[75px] object-contain ml-20' src="src/imagenes/WonkaLogo.png" alt="" />
      </div>
      <Navbar/>
    </header>

    <main className='flex-1'>
      <Productos/>
    </main>

    <footer className='bg-zinc-900 h-20'>

    </footer>
    </div>
  )
}

export default App
