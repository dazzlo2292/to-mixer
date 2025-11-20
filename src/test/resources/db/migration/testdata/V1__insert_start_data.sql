insert into roles (name)
values ('USER'), ('ADMIN');

insert into users(username, password, role_id)
values
('user','$2a$12$FFwZMqWgDjsfgWZr57BhXODCCiKKYpxHVE0BTCX80GlpQGMvtyMTO', 1),
('admin','$2a$12$L5F/FEg7JLj8qWQigfh6eesDeFZhlKc0t1AUU/v7JsNJWchcTiQym', 2);

-- Brands
insert into brands(name, delete_fl)
values ('Test_Brand_1','N'), ('Test_Brand_2','N'), ('Test_Brand_3','N');

-- Tobaccos
insert into tobaccos(name, description, brand_id, strength, base_fl, delete_fl)
values
('Test_Tobacco_1','Test_Tobacco_Desc_1',3,5,'Y','N'),
('Test_Tobacco_2','Test_Tobacco_Desc_2',1,5,'Y','N'),
('Test_Tobacco_3','Test_Tobacco_Desc_3',3,5,'N','N'),
('Test_Tobacco_4','Test_Tobacco_Desc_4',2,4,'N','N'),
('Test_Tobacco_5','Test_Tobacco_Desc_5',1,6,'Y','N');

-- Mixes
insert into mixes (name, delete_fl, day_fl, likes, dislikes)
values ('Test_Mix_1','N','N',0,0),('Test_Mix_2','N','N',0,0),
('Test_Mix_3','N','N',0,0);

-- Tobaccos in Mixes
insert into mixes_tobaccos (mix_id, tobacco_id)
values
(1,1),(1,2),
(2,2),(2,3),
(3,1),(3,3);
