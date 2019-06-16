--заполняем доступные виды транспорта
declare
begin

  insert into newhope.transport_types
    (transport_type_id,
     s_name)
  values
    (1,
     'Автобус');

  insert into newhope.transport_types
    (transport_type_id,
     s_name)
  values
    (2,
     'Троллейбус');

  insert into newhope.transport_types
    (transport_type_id,
     s_name)
  values
    (3,
     'Трамвай');

  insert into newhope.transport_types
    (transport_type_id,
     s_name)
  values
    (4,
     'Маршрутное такси');

  commit;

end;
/

--заполняем оценки по которым будем оценивать (сейчас тольо 5, далее можно предусмотрет любое число и более гибко)
declare
begin

  insert into newhope.estimate_types
    (estimate_type_id,
     s_name)
  values
    (1,
     'Соблюдение графика (точность)');
  insert into newhope.estimate_types
    (estimate_type_id,
     s_name)
  values
    (2,
     'Заполненность транспорта');
  insert into newhope.estimate_types
    (estimate_type_id,
     s_name)
  values
    (3,
     'Техническое состояние транспорта');
  insert into newhope.estimate_types
    (estimate_type_id,
     s_name)
  values
    (4,
     'Соблюдение ПДД');
  insert into newhope.estimate_types
    (estimate_type_id,
     s_name)
  values
    (5,
     'Качество оказания услуг');

  commit;

end;
/

--заполняем доступных пермских перевозчиков
declare
begin
  insert into newhope.shippers
    (shipper_id,
     s_name)
  values
    (1,
     'ПермГорЭлектроТранспорт');

  insert into newhope.shippers
    (shipper_id,
     s_name)
  values
    (2,
     'ПермАвтобус');

  insert into newhope.shippers
    (shipper_id,
     s_name)
  values
    (3,
     'ПермТакси');

  commit;
end;
/

declare
begin
  --добаляем доступных автобусных маршрутов
  --автобусные маршруты
  insert into newhope.routes
    (route_id,
     s_name,
     transport_type_id)
  values
    (1,
     '77',
     1);
  insert into newhope.routes
    (route_id,
     s_name,
     transport_type_id)
  values
    (2,
     '14',
     1);
  insert into newhope.routes
    (route_id,
     s_name,
     transport_type_id)
  values
    (3,
     '68',
     1);
  insert into newhope.routes
    (route_id,
     s_name,
     transport_type_id)
  values
    (4,
     '1',
     1);
  --троллейбусы
  insert into newhope.routes
    (route_id,
     s_name,
     transport_type_id)
  values
    (5,
     '12',
     2);
  insert into newhope.routes
    (route_id,
     s_name,
     transport_type_id)
  values
    (6,
     '5',
     2);
  --трамваи
  insert into newhope.routes
    (route_id,
     s_name,
     transport_type_id)
  values
    (7,
     '6',
     3);
  insert into newhope.routes
    (route_id,
     s_name,
     transport_type_id)
  values
    (8,
     '5',
     3);
  insert into newhope.routes
    (route_id,
     s_name,
     transport_type_id)
  values
    (9,
     '12',
     3);
  --маршрутное такси
  insert into newhope.routes
    (route_id,
     s_name,
     transport_type_id)
  values
    (10,
     '666',
     4);
  commit;
end;
/

--добавляем новых клиентов
declare
begin

  insert into newhope.clients
    (client_id,
     s_name,
     s_phone,
     s_email)
  values
    (1,
     'Антон',
     '+7(999)-111-2222',
     'anton@newhope.ru');
  insert into newhope.clients
    (client_id,
     s_name,
     s_phone,
     s_email)
  values
    (2,
     'Владимир',
     '+7(999)-111-3333',
     'vladimir@newhope.ru');
  insert into newhope.clients
    (client_id,
     s_name,
     s_phone,
     s_email)
  values
    (3,
     'Михаил',
     '+7(999)-111-4444',
     'mikhail@newhope.ru');
  insert into newhope.clients
    (client_id,
     s_name,
     s_phone,
     s_email)
  values
    (4,
     'Илья',
     '+7(999)-111-5555',
     'ilya@newhope.ru');
  commit;
end;
/
