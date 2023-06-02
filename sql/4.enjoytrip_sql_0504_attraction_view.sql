create or replace view view_attraction
as
select *
from attraction_info
left join 
(select content_id, count(*) likes
from attraction_likes
group by content_id) sq_attr_likes
using (content_id)
left join 
(select content_id, avg(attraction_rating) rating
from attraction_rating
group by content_id) sq_attr_rating
using (content_id);


use enjoytrip;
select * from view_hotplace;