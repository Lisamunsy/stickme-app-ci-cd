DELETE FROM stickers;
DELETE FROM sizes;
DELETE FROM aspects;

INSERT INTO sizes 
	(name, logical_order)
	VALUES 
	('Small', 1), ('Medium', 2), ('Big', 3);

INSERT INTO aspects 
	(name, logical_order)
	VALUES 
	('Matte', 1), ('Glossy', 2);

INSERT INTO stickers 
	(name, description, image_url, price, size_id, aspect_id, date_added)
	VALUES
	('HTML 5', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iure nihil accusantium dolorum? Delectus animi ratione vitae assumenda quae temporibus corporis similique officiis ut quod, consequatur sit est ab. Excepturi, quam?', 'html5.png', 9.99, 
		(SELECT s.id FROM sizes s WHERE s.name = 'Small'), 
		(SELECT a.id FROM aspects a WHERE a.name = 'Matte'), now()),
	('MongoDB', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Adipisci hic quasi eligendi, veritatis mollitia, non optio laudantium ratione consequatur ipsum nemo consequuntur modi molestias, cum laboriosam? Debitis deleniti sit et.', 'mongodb.png', 14.99, 
		(SELECT s.id FROM sizes s WHERE s.name = 'Small'), 
		(SELECT a.id FROM aspects a WHERE a.name = 'Glossy'), now()),
	('PostgreSQL', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Alias eveniet, id tempora modi officiis nihil. Eveniet eligendi sed eum nobis alias. Officiis tenetur excepturi corrupti veritatis quos aut a corporis.', 'postgresql.png', 7.99, 
		(SELECT s.id FROM sizes s WHERE s.name = 'Medium'), 
		(SELECT a.id FROM aspects a WHERE a.name = 'Matte'), now()),
	('Spring', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Mollitia inventore animi quidem, tempore molestiae facilis voluptas veniam nostrum, temporibus iste pariatur fugit laborum in quis et repellat hic esse harum.', 'spring.png', 19.99, 
		(SELECT s.id FROM sizes s WHERE s.name = 'Medium'), 
		(SELECT a.id FROM aspects a WHERE a.name = 'Glossy'), now()),
	('Vue 3', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Numquam ipsa aspernatur maiores explicabo mollitia, recusandae quos asperiores eveniet, iusto iure facere sequi. Sapiente nostrum similique quos, consequatur in cumque doloribus.', 'vue3.png', 4.99, 
		(SELECT s.id FROM sizes s WHERE s.name = 'Big'), 
		(SELECT a.id FROM aspects a WHERE a.name = 'Matte'), now());
 