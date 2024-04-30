
const Login = () => {

    return (
        <div className="flex justify-center mt-10">
            <form className="flex flex-col justify-start items-center w-[400px] h-[240px] gap-6 bg-zinc-900" action="">
                <h2 className="text-2xl">INGRESE AQUI</h2>
                <input className="p-2" type="email" placeholder="email@gmail"/>
                <input className="p-2" type="password" placeholder="contraseña"/>
                <button className="p-2 bg-violet-900 hover:bg-indigo-700">Iniciar Sesion</button>
            </form>
        </div>
    )
}
export default Login