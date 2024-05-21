

INSERT INTO public.users VALUES (7, '6b9781e4-9151-49b3-98cd-793be4a949b2', true, 'Jayanagar, 560038', 'Bangalore', 'jerin1223@123.com', 'Jerin1', 'jj', '$2a$08$3aF4yomrf6sTPLbHFhoD8e5il6QjnPA8Q4gDvu/km/wbMdDA5XLo2', NULL, '9800183905', NULL, 'LOCAL');
INSERT INTO public.users VALUES (5, '4cebfff0-a36d-40d6-a8ef-897551d86df9', true, NULL, NULL, 'jerin@gmail.com', 'jerin', 'j', '$2a$08$Yo/IE5EvvGPcUhNJFDul5uXQmlDunWN1003bMfP3CXBt0JC0qYVqu', NULL, NULL, NULL, 'LOCAL');
INSERT INTO public.users VALUES (6, 'f60a6cf7-f3c2-467a-bad5-a45ecd0b5c3f', true, 'Jayanagar, 560038', 'Bangalore', 'jerin@123.com', 'Jerin1', 'jj', '$2a$08$bGT1ywM1TJ78An.zurbhoOyeRsZir0aJ4yizGXeFIgzySk04Hs6xG', NULL, '9800183905', NULL, 'LOCAL');
INSERT INTO public.users VALUES (1, NULL, true, 'Hadapsar', 'Pune', 'admin@gmail.com', 'Admin', 'A', '$2a$08$Yo/IE5EvvGPcUhNJFDul5uXQmlDunWN1003bMfP3CXBt0JC0qYVqu', NULL, '999999999', NULL, 'LOCAL');
INSERT INTO public.users VALUES (8, '100dbf23-933b-42f3-9f6f-df39a3519350', true, NULL, NULL, 'srijit1@gmail2.com', 'Srijit', 'Nair', '$2a$08$b5g/vMMgRRNUT84SmWmDkO3FIEZjGQBe65fiuM6VI18sLn5EYjH1S', NULL, NULL, NULL, 'LOCAL');
INSERT INTO public.users VALUES (9, '736bbd7b-3754-4757-b499-c01ad4798df5', true, NULL, NULL, 'srijitnair1998@gmail.com', 'Srijit', 'Nair', '$2a$08$Y6QuAmT.rD6MqLc3nRvEVOu.aUykbY4Ro4RJG3XDGuMgx3m/xkH4S', NULL, NULL, NULL, 'LOCAL');
INSERT INTO public.users VALUES (10, '822bf6d8-5fb2-48e0-a5b8-43d76a13c7fc', true, NULL, NULL, 'srijitnair1999@gmail.com', 'Srijit', 'Nair', '$2a$08$h5LhE16xxdVNCLCGjdAKSOB.eqi5E/xbNXEBLGd8A0sZSsPV0rwYy', NULL, NULL, NULL, 'LOCAL');


INSERT INTO public.user_role VALUES (1, 'ADMIN');
INSERT INTO public.user_role VALUES (5, 'USER');
INSERT INTO public.user_role VALUES (6, 'USER');
INSERT INTO public.user_role VALUES (7, 'USER');
INSERT INTO public.user_role VALUES (8, 'USER');
INSERT INTO public.user_role VALUES (9, 'USER');
INSERT INTO public.user_role VALUES (10, 'USER');

INSERT INTO public.productcategory OVERRIDING SYSTEM VALUE VALUES (1, 'Fresh Fruits', NULL);
INSERT INTO public.productcategory OVERRIDING SYSTEM VALUE VALUES (2, 'Fresh Vegetables', NULL);
INSERT INTO public.productcategory OVERRIDING SYSTEM VALUE VALUES (3, 'Meat and Seafood', NULL);
INSERT INTO public.productcategory OVERRIDING SYSTEM VALUE VALUES (4, 'Cleaning Essentials', NULL);
INSERT INTO public.productcategory OVERRIDING SYSTEM VALUE VALUES (5, 'Home and Kitchen', NULL);
INSERT INTO public.productcategory OVERRIDING SYSTEM VALUE VALUES (6, 'Beauty and Grooming', NULL);



INSERT INTO public.product (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating) VALUES (19, 'Alphonso Mango', 'https://villkart.com/cdn/shop/products/4_f63bfbb5-0816-47e8-a7d4-c2d97eba50d3.png?v=1676209065', 'Mango', 200, 1, NULL, NULL, NULL, NULL);
INSERT INTO public.product (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating)VALUES (8, 'Chicken breast', 'https://images2.imgbox.com/15/15/gnx0AZtk_o.jpg', 'Chicken', 200, 3, 'A lean cut of poultry meat from the breast of a chicken', 'Poultry', NULL, NULL);
INSERT INTO public.product (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating)VALUES (9, 'Chicken curry cut', 'https://images2.imgbox.com/0b/25/D2Zeym0y_o.jpg', 'Chicken', 230, 3, 'A selection of chicken pieces, typically including a mix of bone-in and boneless cuts, suitable for making curry.', 'Bleach', NULL, NULL);
INSERT INTO public.product (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating)VALUES (10, 'Dettol 200ml', 'https://images2.imgbox.com/fb/ad/s04Zn8Cz_o.jpg', 'Cleaning Liquid', 120, 4, 'A popular antiseptic liquid used for disinfecting surfaces', 'Soap', NULL, NULL);
INSERT INTO public.product (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating) VALUES (11, 'Surf Excel Matic', 'https://images2.imgbox.com/0b/23/YGL2Qqe9_o.jpg', 'Detergent', 170, 4, 'A brand of laundry detergent specifically designed for washing machines', 'veggies', NULL, NULL);
INSERT INTO public.product (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating)VALUES (12, 'Safe wash 150 ml', 'https://images2.imgbox.com/8b/b0/HVlhLRwg_o.jpg', 'Detergent', 132, 4, 'A gentle and pH-balanced intimate wash designed for feminine hygiene', 'Beauty Cosmetics', NULL, NULL);
INSERT INTO public.product (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating)VALUES (4, 'Carrot', 'https://images2.imgbox.com/2a/13/D75czZC2_o.jpg', 'Carrot', 23, 2, 'A crunchy, orange-colored root vegetable with a sweet and earthy flavor', 'Polish', NULL, NULL);
INSERT INTO public.product (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating) VALUES (13, 'Garnier', 'https://images2.imgbox.com/42/fd/wLQwjF8G_o.jpg', 'Garnier', 120, 6, 'A popular brand of skincare, haircare, and beauty products offering a range of cosmetics, shampoos, conditioners, face washes, and moisturizers.', ' Beauty Cosmetics', NULL, NULL);
INSERT INTO public.product (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating) VALUES (14, 'Nail_Lacquer', 'https://images2.imgbox.com/c7/63/FEonhHQf_o.jpg', 'Nail_Lacquer', 70, 6, ' A colored liquid applied to fingernails or toenails to add color and enhance their appearance', 'Beauty Cosmetics', NULL, NULL);
INSERT INTO public.product (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating) VALUES (15, 'Mamaearth_Lipstick', 'https://images2.imgbox.com/61/7f/LAOmZok1_o.jpg', 'Lipstick', 132, 6, 'A cosmetic product designed to add color, moisture, and protection to the lips.', 'Beauty Cosmetics', NULL, NULL);
INSERT INTO public.product  (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating) VALUES (16, 'Car Freshener Gel Relaxing Lavender', 'https://images2.imgbox.com/dc/77/Mp7QOGBu_o.jpg', 'Car Freshener', 120, 5, 'A car air freshener infused with relaxing lavender fragrance to create a soothing atmosphere while driving.', 'Aromatherapy', NULL, NULL);
INSERT INTO public.product  (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating)VALUES (17, 'Power Pocket Bathroom Freshener Assorted Pack', 'https://images2.imgbox.com/f3/f0/U0syir0G_o.jpg', 'Power Pocket Bathroom Freshener Assorted Pack', 70, 5, ' pack of bathroom air fresheners in various scents, designed to eliminate unpleasant odors and create a fresh environment in the bathroom', 'Aromatherapy', NULL, NULL);
INSERT INTO public.product  (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating)VALUES (18, 'Camphor Cone Freshener Lavender', 'https://images2.imgbox.com/bd/4b/0eKqTzeW_o.jpg', 'Camphor Cone Freshener Lavender', 132, 5, 'A cone-shaped air freshener infused with lavender and camphor essential oils to purify the air and create a calming atmosphere', 'Aromatherapy', NULL, NULL);
INSERT INTO public.product  (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating)VALUES (26, 'Capsicum', 'https://sunrisefruits.com/wp-content/uploads/2018/05/Productos-Pimientos-Peppers-Sunrisefruitscompany.jpg', 'Capsicum', 30, 2, NULL, 'Veggies', NULL, NULL);
INSERT INTO public.product (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating) VALUES (5, 'mango', '
https://images2.imgbox.com/e5/32/GOjAQDlw_o.jpg', 'mango', 12, 1, 'A juicy tropical fruit with a sweet and tangy flavor', 'Dev fruits', NULL, NULL);
INSERT INTO public.product  (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating) VALUES (3, 'Onion', 'https://images2.imgbox.com/a5/70/F9tDjUzN_o.jpg', 'onion', 24, 2, 'A bulbous vegetable with layers of papery skin and a pungent flavor when raw', 'Nivi Veggies', NULL, NULL);
INSERT INTO public.product  (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating) VALUES (6, 'apple', 'https://images2.imgbox.com/a7/95/PELMzL1h_o.jpg', 'apple', 90, 1, 'A crisp and juicy fruit with a variety of colors, including red, green, and yellow.', 'Dev fruits', NULL, NULL);
INSERT INTO public.product (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating) VALUES (7, 'watermelon', 'https://images2.imgbox.com/e5/32/GOjAQDlw_o.jpg', 'watermelon', 122, 1, 'A large, juicy fruit with sweet, red flesh and a green rind', 'Dev fruits', NULL, NULL);
INSERT INTO public.product (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating) VALUES (2, 'Banana1', 'https://images2.imgbox.com/11/92/Rhz1oNGv_o.jpg', 'Banana', 34, 1, NULL, NULL, NULL, NULL);
INSERT INTO public.product (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating) VALUES (27, 'wd', 'https://images2.imgbox.com/5b/8e/5khrTbCB_o.jpg', 'ads', 30, 1, NULL, 'umbrella academy', NULL, NULL);
INSERT INTO public.product (id,productname,imagepath,producttag,price,categoryid,description,seller,review,rating) VALUES (1, 'Cabbage', 'https://img.freepik.com/premium-vector/cabbage-image-cute-image-isolated-cabbage-vector-illustration_118339-5280.jpg', 'head_cabbage', 100, 2, 'Fresh organic cabbage', 'fruit Seller', NULL, NULL);


INSERT INTO public.orders(id,address,city,date,email,first_name,last_name,phone_number,post_index,total_price) VALUES (6, 'Hadapsar', 'Bangalore', '2024-05-10', 'jaseemckclt@gmail.com', 'Srijit', 'Nair', '08921039389', NULL, 2068);
INSERT INTO public.orders (id,address,city,date,email,first_name,last_name,phone_number,post_index,total_price) VALUES (7, 'Jayanagar, 560038', 'Bangalore', '2024-05-10', 'jerin@123.com', 'Jerin1', 'jj', '9800183905', NULL, 500);
INSERT INTO public.orders  (id,address,city,date,email,first_name,last_name,phone_number,post_index,total_price)VALUES (8, 'Jayanagar, 560038', 'Bangalore', '2024-05-12', 'jerin@123.com', 'Jerin1', 'jj', '9800183905', NULL, 500);
INSERT INTO public.orders (id,address,city,date,email,first_name,last_name,phone_number,post_index,total_price) VALUES (9, 'Hadapsar', 'Pune', '2024-05-12', 'admin@gmail.com', 'Admin', 'A', '999999999', NULL, 670);
INSERT INTO public.orders (id,address,city,date,email,first_name,last_name,phone_number,post_index,total_price) VALUES (10, 'Jayanagar, 560038', 'Bangalore', '2024-05-15', 'jerin@123.com', 'Jerin1', 'jj', '9800183905', NULL, 500);
INSERT INTO public.orders (id,address,city,date,email,first_name,last_name,phone_number,post_index,total_price) VALUES (11, 'Hadapsar', 'Bangalore', '2024-05-16', 'admin@gmail.com', 'Srijit', 'Nair', '08921039389', NULL, 500);
INSERT INTO public.orders (id,address,city,date,email,first_name,last_name,phone_number,post_index,total_price) VALUES (12, 'Hadapsar', 'Pune', '2024-05-17', 'admin@gmail.com', 'Admin', 'A', '999999999', NULL, 1034);
INSERT INTO public.orders (id,address,city,date,email,first_name,last_name,phone_number,post_index,total_price) VALUES (13, '11/487, Sreekripa, Pathikkal,Kannadi', 'Palakkad', '2024-05-17', 'srijitnair1999@gmail.com', 'Srijit', 'Nair', '08921039389', NULL, 500);
INSERT INTO public.orders (id,address,city,date,email,first_name,last_name,phone_number,post_index,total_price) VALUES (14, 'Hadapsar', 'Bangalore', '2024-05-17', 'srijitnair1999@gmail.com', 'Srijit', 'Nair', '08921039389', NULL, 720);



INSERT INTO public.order_item (id,amount,quantity,product_id)VALUES (12, 2000, 4, 1);
INSERT INTO public.order_item (id,amount,quantity,product_id) VALUES (13, 68, 2, 2);
INSERT INTO public.order_item (id,amount,quantity,product_id) VALUES (14, 500, 1, 1);
INSERT INTO public.order_item (id,amount,quantity,product_id)  VALUES (15, 500, 1, 1);
INSERT INTO public.order_item (id,amount,quantity,product_id) VALUES (16, 500, 1, 1);
INSERT INTO public.order_item (id,amount,quantity,product_id) VALUES (17, 170, 1, 11);
INSERT INTO public.order_item (id,amount,quantity,product_id) VALUES (18, 500, 1, 1);
INSERT INTO public.order_item (id,amount,quantity,product_id) VALUES (19, 500, 1, 1);
INSERT INTO public.order_item (id,amount,quantity,product_id) VALUES (20, 1000, 2, 1);
INSERT INTO public.order_item (id,amount,quantity,product_id) VALUES (21, 34, 1, 2);
INSERT INTO public.order_item (id,amount,quantity,product_id) VALUES (22, 500, 1, 1);
INSERT INTO public.order_item (id,amount,quantity,product_id) VALUES (23, 600, 3, 8);
INSERT INTO public.order_item (id,amount,quantity,product_id) VALUES (24, 120, 1, 10);




INSERT INTO public.orders_order_items(order_id,order_items_id) VALUES (6, 12);
INSERT INTO public.orders_order_items (order_id,order_items_id) VALUES (6, 13);
INSERT INTO public.orders_order_items (order_id,order_items_id) VALUES (7, 14);
INSERT INTO public.orders_order_items (order_id,order_items_id) VALUES (8, 15);
INSERT INTO public.orders_order_items (order_id,order_items_id) VALUES (9, 16);
INSERT INTO public.orders_order_items (order_id,order_items_id) VALUES (9, 17);
INSERT INTO public.orders_order_items (order_id,order_items_id)  VALUES (10, 18);
INSERT INTO public.orders_order_items (order_id,order_items_id) VALUES (11, 19);
INSERT INTO public.orders_order_items (order_id,order_items_id) VALUES (12, 20);
INSERT INTO public.orders_order_items (order_id,order_items_id) VALUES (12, 21);
INSERT INTO public.orders_order_items (order_id,order_items_id) VALUES (13, 22);
INSERT INTO public.orders_order_items (order_id,order_items_id) VALUES (14, 23);
INSERT INTO public.orders_order_items (order_id,order_items_id)  VALUES (14, 24);
