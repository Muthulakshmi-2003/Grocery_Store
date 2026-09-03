import "./AboutUs.scss";

function AboutUs() {
  return (
    <section className="about-us">
      <div className="about-us-content">
        <h2>About KitBlink</h2>

        <p>
          Welcome to KitBlink, your convenient online grocery store designed to
          make everyday shopping simple and hassle-free.
        </p>

        <p>
          We provide fresh vegetables, fruits, dairy products, packaged foods,
          beverages, and household essentials. Our goal is to make grocery
          shopping easier and more convenient for everyone.
        </p>

        <p>
          With KitBlink, customers can browse products, search by category, add
          items to their cart, and place orders from the comfort of their home.
        </p>

        <div className="about-us-features">
          <div>
            <h3>Fresh Products</h3>
            <p>Quality groceries for your everyday needs.</p>
          </div>

          <div>
            <h3>Easy Shopping</h3>
            <p>Browse, search, and shop with ease.</p>
          </div>

          <div>
            <h3>Reliable Service</h3>
            <p>A simple and convenient grocery experience.</p>
          </div>
        </div>
      </div>
    </section>
  );
}

export default AboutUs;
