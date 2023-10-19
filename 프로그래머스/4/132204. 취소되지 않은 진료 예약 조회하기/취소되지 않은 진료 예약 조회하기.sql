SELECT a.apnt_no, p.pt_name, a.pt_no, a.mcdp_cd, d.dr_name, a.apnt_ymd
from appointment as a join patient as p on a.pt_no = p.pt_no
join doctor as d on a.mddr_id = d.dr_id
where datediff(a.apnt_ymd,'2022-04-13') = 0 and a.mcdp_cd='CS' and a.apnt_cncl_yn = 'N'
order by a.apnt_ymd asc;