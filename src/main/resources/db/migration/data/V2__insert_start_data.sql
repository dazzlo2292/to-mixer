insert into roles (name)
values ('USER'), ('ADMIN');

insert into users(username, password, role_id)
values
('user','$2a$12$FFwZMqWgDjsfgWZr57BhXODCCiKKYpxHVE0BTCX80GlpQGMvtyMTO', 1),
('admin','$2a$12$L5F/FEg7JLj8qWQigfh6eesDeFZhlKc0t1AUU/v7JsNJWchcTiQym', 2);

-- Brands
insert into brands(name, delete_fl)
values ('Darkside','N'), ('Musthave','N'), ('Black Burn','N'), ('Deus','N'), ('NАШ','N'),
 ('Palitra','N'), ('Satyr','N'), ('WTO','N'), ('Kraken','N'),('Spectrum','N'),
 ('Bonche','N'), ('Sebero','N');

-- Tobaccos
insert into tobaccos(name, description, brand_id, strength, base_fl, delete_fl)
values ('Green Tea','Зеленый чай',3,5,'Y','N'),
('Virgin Peach','Персик',1,5,'Y','N'),
('Shock Lemon','Кислый лимон',3,5,'N','N'),
('Peppermint','Перечная мята',2,4,'N','N'),
('Green Apple','Зеленое яблока',4,6,'Y','N'),
('Papaya v obed','Сладкая папайя',3,5,'Y','N'),
('Carrot Fresh','Морковный фреш',6,5,'Y','N'),
('Strawberry','Клубника',9,7,'Y','N'),
('Black Currant','Черная смородина',2,4,'Y','N'),
('Кокос','Кокос',5,5,'N','N'),
('Berry Mors','Ягодный морс',2,4,'Y','N'),
('Needls','Елки',1,5,'N','N'),
('Orange Team','Апельсин и мандарин',2,4,'Y','N'),
('Карамель','Карамель',11,7,'N','N'),
('Love is','Клубника и банан',4,6,'Y','N'),
('Kiwi Smoothie','Смузи из киви',2,4,'Y','N'),
('Haribon','Мармелад Кола',3,6,'Y','N'),
('Vanilla Cream','Ванильный крем',2,4,'N','N'),
('Nord Star','Вишня',2,4,'Y','N'),
('Supernova','Холод',1,5,'N','N'),
('Color of India','Карри',12,6,'N','N'),
('Черный перец','Черный перец',5,5,'N','N');

-- Mixes
insert into mixes (name)
values ('Персикой чай Nestea'),('Яблоко + Папайя + Морковь'),
('Клубника + Черная смородина + Кокос + Мята'), ('Таёжный морк'),
('Апельсин + Карамель + Клубника + Банан'), ('Киви + Клубника + Лимон + Мята'),
('Cola Vanilla'), ('Морковь + Яблоко + Карри + Черный перец');

-- Tobaccos in Mixes
insert into mixes_tobaccos (mix_id, tobacco_id)
values
(1,1),(1,2),(1,3),(1,4),
(2,5),(2,6),(2,7),
(3,8),(3,9),(3,10),(3,4),
(4,11),(4,12),(4,4),
(5,13),(5,14),(5,15),
(6,16),(6,8),(6,3),(6,4),
(7,17),(7,18),(7,19),(7,20),
(8,7),(8,5),(8,21),(8,22);
