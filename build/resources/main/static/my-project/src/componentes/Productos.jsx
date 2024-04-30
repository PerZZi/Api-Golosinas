import "../css/fuente.css";
const Productos = () => {

    return (

        <section className="flex justify-around items-center">
            <img className="h-[400px] " src="/src/imagenes/paqueteGomitas.png" alt="" />

            <div className="flex flex-col w-[500px] gap-6">
                <h2 className="text-xl">Un mundo de PURA IMAGINACIÓN</h2>
                <p>¡Descubre las NUEVAS gomitas Wonka ® Magic Hat TM ! ¡Un universo de dulces atemporales y experiencias mágicas para delicias dulces inesperadas! Prueba la magia del mundo deliciosamente mágico de Willy Wonka</p>

                <div className="flex justify-around items-center">
                <button className="w-[200px] h-[50px] bg-yellow-400 text-white hover:bg-violet-900 hover:text-white">Comprar</button>
                <img className="w-64  " src="/src/imagenes/gomitaRellena.png" alt="" />
                </div>
            </div>
        </section>
    )
}
export default Productos