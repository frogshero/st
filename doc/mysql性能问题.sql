show global variables like 'long_query_time'; 
set global slow_query_log = on;
set global slow_query_log_file = 'whwt_slow_query.log';   
set global long_query_time = 5;
wrk_stock_detail.group_id

select * from wrk_stock_back_weight
where create_time>'2021-03-19'