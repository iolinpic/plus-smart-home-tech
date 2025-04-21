CREATE TABLE IF NOT EXISTS payment (
   payment_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
   order_id UUID NOT NULL,
   total_payment DOUBLE NOT NULL,
   delivery_total DOUBLE NOT NULL,
   fee_total DOUBLE NOT NULL,
   payment_state VARCHAR NOT NULL
);