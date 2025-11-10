insert into roles (name)
values ('USER'), ('ADMIN');

insert into users(username, password, role_id)
values
('user','$2a$12$FFwZMqWgDjsfgWZr57BhXODCCiKKYpxHVE0BTCX80GlpQGMvtyMTO', 1),
('admin','$2a$12$L5F/FEg7JLj8qWQigfh6eesDeFZhlKc0t1AUU/v7JsNJWchcTiQym', 2);

insert into brands(name)
values ('Darkside'), ('Musthave'), ('Black Burn'), ('Deus'), ('NАШ'),
 ('Palitra'), ('Satyr'), ('WTO'), ('Kraken'),('Spectrum'),
 ('Bonche'), ('Sebero');

insert into tobaccos(name, description, brand_id, strength, base_fl)
values ('Green Tea','Зеленый чай',3,5,'Y'),
('Virgin Peach','Персик',1,5,'Y'),
('Shock Lemon','Кислый лимон',3,5,'N'),
('Peppermint','Перечная мята',2,4,'N'),
('Green Apple','Зеленое яблока',4,6,'Y'),
('Papaya v obed','Сладкая папайя',3,5,'Y'),
('Carrot Fresh','Морковный фреш',6,5,'Y'),
('Strawberry','Клубника',9,7,'Y'),
('Black Currant','Черная смородина',2,4,'Y'),
('Кокос','Кокос',5,5,'N'),
('Berry Mors','Ягодный морс',2,4,'Y'),
('Needls','Елки',1,5,'N'),
('Orange Team','Апельсин и мандарин',2,4,'Y'),
('Карамель','Карамель',11,7,'N'),
('Love is','Клубника и банан',4,6,'Y'),
('Kiwi Smoothie','Смузи из киви',2,4,'Y'),
('Haribon','Мармелад Кола',3,6,'Y'),
('Vanilla Cream','Ванильный крем',2,4,'N'),
('Nord Star','Вишня',2,4,'Y'),
('Supernova','Холод',1,5,'N'),
('Color of India','Карри',12,6,'N'),
('Черный перец','Черный перец',5,5,'N');
