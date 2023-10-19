-- 코드를 입력하세요

SELECT 
car.car_id, 
car.car_type, 
round(daily_fee*(1-discount_rate/100)*30, 0) as fee
from car_rental_company_car as car
join car_rental_company_discount_plan as plan
on car.car_type = plan.car_type
where car.car_type in ('세단', 'SUV') and duration_type="30일 이상"
and daily_fee*(1-discount_rate/100)*30 between 500000 and 2000000
and car_id not in (
    select c.car_id
    from car_rental_company_car as c join car_rental_company_rental_history as history
    on c.car_id = history.car_id
    where datediff(start_date, '2022-11-01')>=0 or datediff(end_date, '2022-11-01') >= 0
)
order by fee desc, car_type asc, car.car_id desc;