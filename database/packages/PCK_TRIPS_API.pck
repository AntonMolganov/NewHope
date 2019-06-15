create or replace package pck_trips_api is
  --api для вставки инфо о поездках

  --процедура вставки новой поездки
  procedure p_create(i_client_id   in clients.client_id%type,
                     i_route_id    in routes.route_id%type,
                     i_shipper_id  in shippers.shipper_id%type,
                     i_d_trip_date in trips.d_trip_date%type,
                     --
                     i_i_compliance_level   in estimates.i_value%type,
                     i_s_compliance_comment in estimates.s_comment%type,
                     --
                     i_i_free_space_level   in estimates.i_value%type,
                     i_s_free_space_comment in estimates.s_comment%type,
                     --
                     i_i_tech_state_level   in estimates.i_value%type,
                     i_s_tech_state_comment in estimates.s_comment%type,
                     --
                     i_i_law_violence_level   in estimates.i_value%type,
                     i_s_law_violence_comment in estimates.s_comment%type,
                     --
                     i_i_service_level   in estimates.i_value%type,
                     i_s_service_comment in estimates.s_comment%type,
                     --
                     o_s_err_code out varchar2,
                     o_s_err_text out varchar2);

end pck_trips_api;
/
create or replace package body pck_trips_api is
  
  gc_ok_result  constant varchar2(30 char) := 'OK';
  gc_err_result constant varchar2(30 char) := 'ERROR';

  --тип "поездка"
  type t_trip is record(
    trip_id trips.trip_id%type,
    --
    client_id   clients.client_id%type,
    route_id    routes.route_id%type,
    shipper_id  shippers.shipper_id%type,
    d_trip_date trips.d_trip_date%type,
    --
    i_compliance_level   estimates.i_value%type,
    s_compliance_comment estimates.s_comment%type,
    --
    i_free_space_level   estimates.i_value%type,
    s_free_space_comment estimates.s_comment%type,
    --
    i_tech_state_level   estimates.i_value%type,
    s_tech_state_comment estimates.s_comment%type,
    --
    i_law_violence_level   estimates.i_value%type,
    s_law_violence_comment estimates.s_comment%type,
    --
    i_service_level   estimates.i_value%type,
    s_service_comment estimates.s_comment%type);

  --вставка новой поездки
  procedure p_create(i_client_id   in clients.client_id%type,
                     i_route_id    in routes.route_id%type,
                     i_shipper_id  in shippers.shipper_id%type,
                     i_d_trip_date in trips.d_trip_date%type,
                     --
                     i_i_compliance_level   in estimates.i_value%type,
                     i_s_compliance_comment in estimates.s_comment%type,
                     --
                     i_i_free_space_level   in estimates.i_value%type,
                     i_s_free_space_comment in estimates.s_comment%type,
                     --
                     i_i_tech_state_level   in estimates.i_value%type,
                     i_s_tech_state_comment in estimates.s_comment%type,
                     --
                     i_i_law_violence_level   in estimates.i_value%type,
                     i_s_law_violence_comment in estimates.s_comment%type,
                     --
                     i_i_service_level   in estimates.i_value%type,
                     i_s_service_comment in estimates.s_comment%type,
                     o_s_err_code        out varchar2,
                     o_s_err_text        out varchar2) is
    l_trip t_trip;
  begin
    --присвоения
    l_trip.trip_id                := newhope.seq_trips.nextval;
    l_trip.client_id              := i_client_id;
    l_trip.route_id               := i_route_id;
    l_trip.shipper_id             := i_shipper_id;
    l_trip.d_trip_date            := i_d_trip_date;
    l_trip.i_compliance_level     := i_i_compliance_level;
    l_trip.s_compliance_comment   := i_s_compliance_comment;
    l_trip.i_free_space_level     := i_i_free_space_level;
    l_trip.s_free_space_comment   := i_s_free_space_comment;
    l_trip.i_tech_state_level     := i_i_tech_state_level;
    l_trip.s_tech_state_comment   := i_s_tech_state_comment;
    l_trip.i_law_violence_level   := i_i_law_violence_level;
    l_trip.s_law_violence_comment := i_s_law_violence_comment;
    l_trip.i_service_level        := i_i_service_level;
    l_trip.s_service_comment      := i_s_service_comment;
    --сама поездка
    insert into trips
      (trip_id,
       client_id,
       route_id,
       shipper_id,
       d_trip_date)
    values
      (l_trip.trip_id,
       l_trip.client_id,
       l_trip.route_id,
       l_trip.shipper_id,
       l_trip.d_trip_date);
  
    --добавляем только НЕ-пустые оценки
    if l_trip.i_compliance_level is not null then
      --соблюдение графика
      insert into estimates
        (estimate_id,
         estimate_type_id,
         trip_id,
         i_value,
         s_comment)
      values
        (seq_estimates.nextval,
         1,
         l_trip.trip_id,
         l_trip.i_compliance_level,
         l_trip.s_compliance_comment);
    end if;
    --
    if l_trip.i_free_space_level is not null then
      --заполненность транспорта
      insert into estimates
        (estimate_id,
         estimate_type_id,
         trip_id,
         i_value,
         s_comment)
      
      values
        (seq_estimates.nextval,
         2,
         l_trip.trip_id,
         l_trip.i_free_space_level,
         l_trip.s_free_space_comment);
    
    end if;
    --
    if l_trip.i_tech_state_level is not null then
      --техническое состояние транспорта
      insert into estimates
        (estimate_id,
         estimate_type_id,
         trip_id,
         i_value,
         s_comment)
      values
        (seq_estimates.nextval,
         3,
         l_trip.trip_id,
         l_trip.i_tech_state_level,
         l_trip.s_tech_state_comment);
    end if;
    --
    if l_trip.i_law_violence_level is not null then
      --соблюдение ПДД
      insert into estimates
        (estimate_id,
         estimate_type_id,
         trip_id,
         i_value,
         s_comment)
      values
        (seq_estimates.nextval,
         4,
         l_trip.trip_id,
         l_trip.i_law_violence_level,
         l_trip.s_law_violence_comment);
    end if;
    --
    if l_trip.i_service_level is not null then
      --качество оказания услуг
      insert into estimates
        (estimate_id,
         estimate_type_id,
         trip_id,
         i_value,
         s_comment)
      values
        (seq_estimates.nextval,
         5,
         l_trip.trip_id,
         l_trip.i_service_level,
         l_trip.s_service_comment);
    end if;
    --успешно вставили
    o_s_err_code := gc_ok_result;
  
  exception
    when others then
      --здесь можем залогировать ошибки чтобы видеть самим, что сервис не работает
      o_s_err_code := gc_err_result;
      o_s_err_text := 'Во время сохранения вашего комментария произошла ошибка. Мы над этим работаем!';
  end p_create;

end pck_trips_api;
/
