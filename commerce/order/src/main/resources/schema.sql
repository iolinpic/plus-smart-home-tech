CREATE TABLE IF NOT EXISTS orders (
    order_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR NOT NULL,
    shopping_cart_id UUID NOT NULL,
    payment_id UUID,
    delivery_id UUID,
    state VARCHAR NOT NULL,
    delivery_weight DOUBLE,
    delivery_volume DOUBLE,
    fragile BOOLEAN,
    total_price DOUBLE,
    delivery_price DOUBLE,
    product_price DOUBLE
);

CREATE TABLE IF NOT EXISTS order_products (
    order_id UUID REFERENCES orders(order_id),
    product_id UUID,
    quantity INT,
    PRIMARY KEY (order_id, product_id)
);