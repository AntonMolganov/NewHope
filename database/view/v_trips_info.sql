create or replace force view newhope.v_trips_info as
select
/**
  $Date:$
  $Path:$
**/
--сведения о поездке
 t.trip_id,
 t.d_trip_date,
 --сведения о клиенте
 c.client_id,
 c.s_name    as s_client_name,
 c.s_phone   as s_phone,
 c.s_email   as s_email,
 --данные о т/с, маршруте, перевозчике
 r.route_id,
 r.s_name   as s_route_number,
 --
 tt.transport_type_id,
 tt.s_name            as s_transport_type,
 --
 t.shipper_id,
 s.s_name     as s_shipper_name,
 --оценки
 e1.i_value   as i_compliance_level,
 e1.s_comment as s_compliance_comment,
 --
 e2.i_value   as i_free_space_level,
 e2.s_comment as s_free_space_comment,
 --
 e3.i_value   as i_tech_state_level,
 e3.s_comment as s_tech_state_comment,
 --
 e4.i_value   as i_law_violence_level,
 e4.s_comment as s_law_comment,
 --
 e5.i_value   as i_service_level,
 e5.s_comment as s_service_comment

  from newhope.trips t
  join newhope.clients c
    on c.client_id = t.client_id
  join newhope.routes r
    on r.route_id = t.route_id
  join newhope.transport_types tt
    on tt.transport_type_id = r.transport_type_id
  join newhope.shippers s
    on s.shipper_id = t.shipper_id
  left join newhope.estimates e1
    on e1.trip_id = t.trip_id
   and e1.estimate_type_id = 1
  left join newhope.estimates e2
    on e2.trip_id = t.trip_id
   and e2.estimate_type_id = 2
  left join newhope.estimates e3
    on e3.trip_id = t.trip_id
   and e3.estimate_type_id = 3
  left join newhope.estimates e4
    on e4.trip_id = t.trip_id
   and e4.estimate_type_id = 4
  left join newhope.estimates e5
    on e5.trip_id = t.trip_id
   and e5.estimate_type_id = 5;

comment on table newhope.v_trips_info is 'Инфо о поездках';
