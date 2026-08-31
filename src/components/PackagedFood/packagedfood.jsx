import "../../styles/Variable.scss";


import maggi from "../../assets/maggi.jpg";
import noddles from "../../assets/maggi.jpg";
import pasta from "../../assets/maggi.jpg";
import maggi_pasta from "../../assets/maggi.jpg";

function Packaged(){
    const packaged = [
        {
            image:maggi ,
            name: "Spicy maggi",
            quantity: "1",
            price: 120
        },
        {
            image: noddles,
            name: "Hakka noddles",
            quantity: "1",
            price: 60
        },
        {
            image: pasta,
            name: "Spicy pasta",
            quantity: "1",
            price: 90
        },
        {
            image: maggi_pasta,
            name: "Hot chilly maggi pasta",
            quantity: "1",
            price: 80
        }
    ];

    return (
        <section className="product-section">

            <div className="section-header">
                <h2>Packaged Food </h2>

                <a href="#">See All</a>
            </div>

            <div className="product-grid">

                {packaged.map((product, index) => (

                    <div className="product-card" key={index}>

                        <div className="product-image">
                            <img
                                src={product.image}
                                alt={product.name}
                            />
                        </div>

                        <h3>{product.name}</h3>

                        <p>{product.quantity}</p>

                        <strong>₹{product.price}</strong>

                        <button>Add</button>

                    </div>

                ))}

            </div>

        </section>
    );
}

export default Packaged;