CREATE TABLE IF NOT EXISTS warehouse_product (
    product_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    weight DOUBLE,
    width DOUBLE,
    height DOUBLE,
    depth DOUBLE,
    fragile BOOLEAN,
    quantity INT
);

CREATE TABLE IF NOT EXISTS bookings (
    booking_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    fragile BOOLEAN,
    delivery_volume DOUBLE NOT NULL,
    delivery_weight DOUBLE NOT NULL,
    delivery_id UUID,
    order_id UUID NOT NULL
);

CREATE TABLE IF NOT EXISTS booking_products (
    booking_id UUID REFERENCES bookings(booking_id) ON DELETE CASCADE,
    product_id UUID REFERENCES warehouse_product(product_id) ON DELETE CASCADE,
    quantity INTEGER NOT NULL
);