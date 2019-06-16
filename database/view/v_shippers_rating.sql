create or replace force view newhope.v_shippers_rating as
select
/**
$Date:$
$Path:$
**/

 an.shipper_id,
 an.s_shipper_name,
 an.n_avg_compliance_level,
 n_avg_free_space_level,
 n_avg_tech_state_level,
 n_avg_law_violence_level,
 n_avg_service_level
  from (select s.shipper_id,
               s.s_name as s_shipper_name,
               round(avg(decode(e.estimate_type_id,
                                1,
                                e.i_value,
                                null)) over(partition by s.shipper_id),
                     2) as n_avg_compliance_level,
               round(avg(decode(e.estimate_type_id,
                                2,
                                e.i_value,
                                null)) over(partition by s.shipper_id),
                     2) as n_avg_free_space_level,
               round(avg(decode(e.estimate_type_id,
                                3,
                                e.i_value,
                                null)) over(partition by s.shipper_id),
                     2) as n_avg_tech_state_level,
               round(avg(decode(e.estimate_type_id,
                                4,
                                e.i_value,
                                null)) over(partition by s.shipper_id),
                     2) as n_avg_law_violence_level,
               round(avg(decode(e.estimate_type_id,
                                5,
                                e.i_value,
                                null)) over(partition by s.shipper_id),
                     2) as n_avg_service_level,
               row_number() over(partition by s.shipper_id order by s.shipper_id) as rn
          from newhope.estimates e
          join newhope.trips t
            on t.trip_id = e.trip_id
          join newhope.shippers s
            on s.shipper_id = t.shipper_id
        
        ) an
 where an.rn = 1;

comment on table newhope.v_shippers_rating is 'Рейтинг перевозчиков по мнению пользователей';
