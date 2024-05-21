DO $$
DECLARE
    maxid INTEGER;
BEGIN
    SELECT max(id) INTO maxid FROM product;

    EXECUTE format('ALTER SEQUENCE %I RESTART WITH %s', 'product_id_seq', maxid+1);

    SELECT max(id) INTO maxid FROM order_item;

    EXECUTE format('ALTER SEQUENCE %I RESTART WITH %s', 'order_item_seq', maxid+1);


	SELECT max(id) INTO maxid FROM orders;

    EXECUTE format('ALTER SEQUENCE %I RESTART WITH %s', 'orders_seq', maxid+1);

	SELECT max(id) INTO maxid FROM productcategory;

    EXECUTE format('ALTER SEQUENCE %I RESTART WITH %s', 'productcategory_id_seq', maxid+1);

 	SELECT max(id) INTO maxid FROM users;

    EXECUTE format('ALTER SEQUENCE %I RESTART WITH %s', 'users_id_seq', maxid+1);
END $$;