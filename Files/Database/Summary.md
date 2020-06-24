[TOC]



## 常见问题

- 排名问题
  1. 普通排名
  
     ```sql
     -- 定义一个变量curRank表示当前的排名
     -- 思路：遍历所有记录，因为没有重复元素，curRank每次自增1即可
     
     SELECT 
     	pid, name, age, @curRank := @curRank + 1 AS rank
     FROM players p, (
     SELECT @curRank := 0
     ) q
     ORDER BY age
     ```
  
  2. 普通并列排名
  
     ```sql
     -- 定义两个变量prevRank，curRank
     -- prevRank：表示上一个age
     -- curRank：表示当前排名
     -- 思路：遍历所有记录，如果当前的age等于上一个age，则排名不需要变化；如果不等于，则当前排名为curRank加1，并且curRank需要自增1
     
     SELECT
     	pid, name, age, 
     	CASE 
     	WHEN @prevRank = age
     		THEN @curRank 
     	WHEN @prevRank := age
     		THEN @curRank := @curRank + 1
     	END AS rank
     FROM players p,
     ( SELECT @curRank :=0, @prevRank := NULL ) r
     ORDER BY age
     ```
  
  3. 高级并列排名
  
     ```sql
     -- 定义三个变量prevRank，curRank，inRank
     -- prevRank：表示上一个age
     -- curRank：表示当前排名
     -- incRank：表示当前已经排名的个数
     -- 思路：遍历所有记录，如果当前的age等于上一个age，则排名不需要变化，incRank需要自增1；如果不等于则
     
     SELECT
     	pid, name, age, rank
     FROM
     	(SELECT 
          	pid, name, age,
     		@curRank := IF( @prevRank = age, @curRank, @incRank ) AS rank, 
     		@incRank := @incRank + 1,
     		@prevRank := age
     		FROM players p, (
  		SELECT @curRank :=0, @prevRank := NULL, @incRank := 1 ) r 
     	ORDER BY age) s
     ```
     
     
  
- 连续相同数字或者连续递增加一数字

  ```sql
  Logs 表:
  +----+-----+
  | Id | Num |
  +----+-----+
  | 1  |  1  |
  | 2  |  1  |
  | 3  |  1  |
  | 4  |  2  |
  | 5  |  1  |
  | 6  |  2  |
  | 7  |  2  |
  +----+-----+
  
  Result 表
  +-----------------+
  | ConsecutiveNums |
  +-----------------+
  | 1               |
  +-----------------+
  
  
  -- 定义两个变量pre，count
  -- pre：表示上一个Num
  -- count：表示已经连续的数量
  -- 思路：遍历所有记录，如果当前Num和pre相等，则count自增1；否则count重置为1。最后筛选出所有大于三的记录，并且用DISTINCT修饰
  
  SELECT
      DISTINCT Num ConsecutiveNums 
  FROM ( 
      SELECT
          Num, if(@pre = Num, @count := @count + 1, @count := 1) nums, @pre := Num
      FROM
          Logs l, (SELECT @pre := null, @count := 1) pc
      ) n
  WHERE
      nums >= 3;
  ```

  

- 逐步求和，前1个数的和，前2个数的和

  ```sql
  -- 查询玩家到目前为止玩了几个游戏
  
  Activity 表:
  +-----------+-----------+------------+--------------+
  | player_id | device_id | event_date | games_played |
  +-----------+-----------+------------+--------------+
  | 1         | 2         | 2016-03-01 | 5            |
  | 1         | 2         | 2016-05-02 | 6            |
  | 1         | 3         | 2017-06-25 | 1            |
  | 3         | 1         | 2016-03-02 | 0            |
  | 3         | 4         | 2018-07-03 | 5            |
  +-----------+-----------+------------+--------------+
  
  Result 表:
  +-----------+------------+---------------------+
  | player_id | event_date | games_played_so_far |
  +-----------+------------+---------------------+
  | 1         | 2016-03-01 | 5                   |
  | 1         | 2016-05-02 | 11                  |
  | 1         | 2017-06-25 | 12                  |
  | 3         | 2016-03-02 | 0                   |
  | 3         | 2018-07-03 | 5                   |
  +-----------+------------+---------------------+
  
  
  -- 思路：自联结并限制条件，t1表的日期大于等于t2表的
  
  SELECT
  	a1.player_id, a1.event_date, sum(a2.games_played) games_played_so_far
  FROM
  	Activity a1 JOIN Activity a2
  ON a1.player_id=a2.player_id AND a1.event_date >= a2.event_date
  GROUP BY a1.player_id, a1.event_date
  ```
  
- 删除重复的记录，保留Id最小的那个

  ```sql
  Person 表:
  +----+------------------+
  | Id | Email            |
  +----+------------------+
  | 1  | john@example.com |
  | 2  | bob@example.com  |
  | 3  | john@example.com |
  +----+------------------+
  Id 是这个表的主键。
  
  Result 表:
  +----+------------------+
  | Id | Email            |
  +----+------------------+
  | 1  | john@example.com |
  | 2  | bob@example.com  |
  +----+------------------+
  
  
  -- 思路：通过内联结，选出那些Email相同并且Id较大的，删除即可
  
  DELETE
  	p1 
  FROM
  	Person p1 JOIN Person p2
  ON
  	p1.Email = p2.Email AND p1.Id > p2.Id
  ```

  

- 求第n高的记录

  1. 如果有多个只返回一个

     > `ORDER BY`然后再`LIMIT n, 1`输出

  2. 如果有多个就返回多个

     > 先用`ORDER BY`然后再`LIMIT n, 1`输出第n高的数，再用`WHERE`限制条件

- 筛选出所有当前的气温大于前一天的气温的Id

  ```sql
  weather 表：
  +---------+------------------+------------------+
  | Id(INT) | RecordDate(DATE) | Temperature(INT) |
  +---------+------------------+------------------+
  |       1 |       2015-01-01 |               10 |
  |       2 |       2015-01-02 |               25 |
  |       3 |       2015-01-03 |               20 |
  |       4 |       2015-01-04 |               30 |
  +---------+------------------+------------------+
  
  Result 表：
  +----+
  | Id |
  +----+
  |  2 |
  |  4 |
  +----+
  
  -- 直接用内联结
  
  SELECT
  	w1.id AS 'Id'
  FROM
  	weather w1 JOIN weather w2
  ON
  	DATEDIFF(w1.RecordDate, w2.RecordDate) = 1 AND w1.Temperature > w2.Temperature
  ```

- 查找最小日期对应的记录上的其他元素

  ```sql
  -- 描述每一个玩家首次登陆的设备名称
  Activity 表:
  +-----------+-----------+------------+--------------+
  | player_id | device_id | event_date | games_played |
  +-----------+-----------+------------+--------------+
  | 1         | 2         | 2016-03-01 | 5            |
  | 1         | 2         | 2016-05-02 | 6            |
  | 2         | 3         | 2017-06-25 | 1            |
  | 3         | 1         | 2016-03-02 | 0            |
  | 3         | 4         | 2018-07-03 | 5            |
  +-----------+-----------+------------+--------------+
  
  Result 表：
  +-----------+-----------+
  | player_id | device_id |
  +-----------+-----------+
  | 1         | 2         |
  | 2         | 3         |
  | 3         | 1         |
  +-----------+-----------+
  
  
  -- 思路：先查找出每个Id和Id对应的最小日期作为子表与原表联结，然后抽取原表的所需字段
  
  SELECT
  	a.player_id, a.device_id
  FROM Activity a JOIN(
      SELECT player_id, min(event_date) min_event_date
      FROM Activity
      GROUP BY player_id
  ) b
  ON a.player_id = b.player_id and a.event_date = b.min_event_date
  ```

- 查找首次登陆并且第二天也登录的玩家占所有玩家的比例

  ```sql
  Activity 表:
  +-----------+-----------+------------+--------------+
  | player_id | device_id | event_date | games_played |
  +-----------+-----------+------------+--------------+
  | 1         | 2         | 2016-03-01 | 5            |
  | 1         | 2         | 2016-03-02 | 6            |
  | 2         | 3         | 2017-06-25 | 1            |
  | 3         | 1         | 2016-03-02 | 0            |
  | 3         | 4         | 2018-07-03 | 5            |
  +-----------+-----------+------------+--------------+
  
  Result 表:
  +-----------+
  | fraction  |
  +-----------+
  | 0.33      |
  +-----------+
  
  -- 思路，先查找出所有玩家Id和首次登陆的时间作为t1左联结原表，联结条件为Id相等并且时间相差一天
  -- AVG(a.SELECTevent_date is not null)表示不为空的event_date占所有event_date的比例
  
  SELECT
  	ROUND(AVG(a.event_date is not null), 2) fraction
  from (
  	SELECT player_id, min(event_date) as login
      FROM activity
      GROUP BY player_id
      ) p  LEFT JOIN activity a
  ON
  	p.player_id = a.player_id AND DATEDIFF(a.event_date, p.login) = 1
  ```
  
- 两个字段需要满足不同的条件

  ```sql
  写一个查询语句，将 2016 年 (TIV_2016) 所有成功投资的金额加起来，保留 2 位小数。
  对于一个投保人，他在 2016 年成功投资的条件是：
  	他在 2015 年的投保额 (TIV_2015) 至少跟一个其他投保人在 2015 年的投保额相同。
  	他所在的城市必须与其他投保人都不同（也就是说维度和经度不能跟其他任何一个投保人完全相同）。
  	
  insurance 表
  | PID | TIV_2015 | TIV_2016 | LAT | LON |
  |-----|----------|----------|-----|-----|
  | 1   | 10       | 5        | 10  | 10  |
  | 2   | 20       | 20       | 20  | 20  |
  | 3   | 10       | 30       | 20  | 20  |
  | 4   | 10       | 40       | 40  | 40  |
  
  
  Result 表
  | TIV_2016 |
  |----------|
  | 45.00    |
  
  
  -- 思路：将两个条件分别用两个子查询实现，然后用WHERE去筛选
  SELECT
  	SUM(insurance.TIV_2016) AS TIV_2016
  FROM
  	insurance
  WHERE 
  	insurance.TIV_2015 IN(
        SELECT TIV_2015
        FROM insurance
        GROUP BY TIV_2015
        HAVING COUNT(*) > 1
      ) AND CONCAT(LAT, LON) IN (
        SELECT CONCAT(LAT, LON)
        FROM insurance
        GROUP BY LAT , LON
        HAVING COUNT(*) = 1
      )
  ```

  

- 谁有最多的好友

  ```sql
  表 request_accepted 存储了所有好友申请通过的数据记录，求出谁拥有最多的好友和他拥有的好友数目。
  
  request_accepted 表：
  | requester_id | accepter_id | accept_date|
  |--------------|-------------|------------|
  | 1            | 2           | 2016_06-03 |
  | 1            | 3           | 2016-06-08 |
  | 2            | 3           | 2016-06-08 |
  | 3            | 4           | 2016-06-09 |
  
  Result 表：
  | id | num |
  |----|-----|
  | 3  | 3   |
  
  -- 思路：通过UNION连接，将数据量翻倍，此时可以通过该表可以表示每个人的所有好友；如果有多个则需要将下列查询结果作为筛选条件。
  
  SELECT
  	rid id, COUNT(aid) num
  FROM(
  	SELECT R1.requester_id rid, R1.accepter_id aid
  	FROM request_accepted R1
  	UNION
  	SELECT R2.accepter_id rid,R2.requester_id aid
  	FROM request_accepted R2
  ) A
  GROUP BY rid
  ORDER BY num DESC
  LIMIT 1
  ```
  
- 连续空余座位

  ```mysql
  -- 查询连续座位，两个相邻空位
  
  cinema 表：
  | seat_id | free |
  |---------|------|
  | 1       | 1    |
  | 2       | 0    |
  | 3       | 1    |
  | 4       | 1    |
  | 5       | 1    |
  
  Result 表：
  | seat_id |
  |---------|
  | 3       |
  | 4       |
  | 5       |
  
  -- 思路：用差值的绝对值为1和两个都是空位为联结条件去联结，并且最终结果用DISTINCT去重
  
  SELECT
  	DISTINCT a.seat_id
  FROM
  	cinema a JOIN cinema b
  ON 
  	ABS(a.seat_id - b.seat_id) = 1 AND a.free = true AND b.free = true
  ORDER BY a.seat_id
  ```

- 树的结点

  ```sql
  -- tree Table表示id的父节点为p_id,将所有的节点分类
  
  tree 表：
  +----+------+
  | id | p_id |
  +----+------+
  | 1  | null |
  | 2  | 1    |
  | 3  | 1    |
  | 4  | 2    |
  | 5  | 2    |
  +----+------+
  
  Result 表：
  +----+------+
  | id | Type |
  +----+------+
  | 1  | Root |
  | 2  | Inner|
  | 3  | Leaf |
  | 4  | Leaf |
  | 5  | Leaf |
  +----+------+
  
  -- 思路：将tree表进行左自联结，如果t1.p_id为空，则该节点为'Root'；如果t2.id为空，则该节点为'Leaf'；否则该节点为'Inner'
  
  SELECT
      DISTINCT t1.id, 
          CASE 
          WHEN t1.p_id is null THEN 'Root'
          WHEN t2.id is null THEN 'Leaf'
          ELSE 'Inner' END Type
  FROM tree t1 LEFT JOIN tree t2
  ON
  	t1.id = t2.p_id
  ```

- 平面上的最近距离

  ```sql
  -- point_2d Table保存了所有的点，求出两个点之间的最近距离
  
  point_2d 表：
  | x  | y  |
  |----|----|
  | -1 | -1 |
  | 0  | 0  |
  | -1 | -2 |
  
  Result 表：
  | shortest |
  |----------|
  | 1.00     |
  ORAND
  
  
  -- 思路：将表进行内外联结，联结条件为 p1.x<p2.x OR (p1.x = p2.x AND p1.y < p2.y)
  -- 这样避免的重复的计算，因为点1到点2的距离和点2到点1的距离是相等的
  
  SELECT 
      ROUND(MIN(SQRT(POWER(p1.x-p2.x, 2) + POWER(p1.y-p2.y, 2))), 2) shortest
  FROM 
      point_2d p1 join point_2d p2
  ON p1.x < p2.x OR (p1.x = p2.x AND p1.y < p2.y)
  ```

- 改变相邻俩学生的座位(只要输出即可)，如果学生人数是奇数，则不需要改变最后一个同学的座位。

  ```sql
  Student 表：
  +---------+---------+
  |    id   | student |
  +---------+---------+
  |    1    | Abbot   |
  |    2    | Doris   |
  |    3    | Emerson |
  |    4    | Green   |
  |    5    | Jeames  |
  +---------+---------+
  
  Result 表：
  +---------+---------+
  |    id   | student |
  +---------+---------+
  |    1    | Doris   |
  |    2    | Abbot   |
  |    3    | Green   |
  |    4    | Emerson |
  |    5    | Jeames  |
  +---------+---------+
  
  -- 思路，交换学生的Id后再排序，如果为奇数且当前Id不等于最大的Id，输出Id+1；如果为奇数且当前Id等于最大的Id，输出Id；如果Id为偶数，则输出Id-1
  
  SELECT
      (CASE
          WHEN MOD(id, 2) != 0 AND counts != id
              THEN id + 1
          WHEN MOD(id, 2) != 0 AND counts = id
              THEN id
          ELSE id - 1
      END) id,
      student
  FROM
      seat,
      (SELECT COUNT(*) AS counts FROM seat) seat_counts
  ORDER BY id
  ```

- 更新数据

  ```sql
  -- 如果sex为'f'，则改为'm'；如果为'm'，则改为'f'
  
  UPDATE salary 
  SET sex = IF(sex='f','m','f')
  ```

- 查询从 `2019-06-30` 起最多 90 天内，每个日期该日期首次登录的用户数。

  ```sql
  Traffic 表：
  +---------+----------+---------------+
  | user_id | activity | activity_date |
  +---------+----------+---------------+
  | 1       | login    | 2019-05-01    |
  | 1       | homepage | 2019-05-01    |
  | 1       | logout   | 2019-05-01    |
  | 2       | login    | 2019-06-21    |
  | 2       | logout   | 2019-06-21    |
  | 3       | login    | 2019-01-01    |
  | 3       | jobs     | 2019-01-01    |
  | 3       | logout   | 2019-01-01    |
  | 4       | login    | 2019-06-21    |
  | 4       | groups   | 2019-06-21    |
  | 4       | logout   | 2019-06-21    |
  | 5       | login    | 2019-03-01    |
  | 5       | logout   | 2019-03-01    |
  | 5       | login    | 2019-06-21    |
  | 5       | logout   | 2019-06-21    |
  +---------+----------+---------------+
  
  Result 表：
  +------------+-------------+
  | login_date | user_count  |
  +------------+-------------+
  | 2019-05-01 | 1           |
  | 2019-06-21 | 2           |
  +------------+-------------+
  
  
  -- 思路：找到所有玩家第一次登陆游戏的时间，然后限制搜索条件
  
  SELECT
  	login_date, count(user_id) user_count
  FROM (
      SELECT user_id, min(activity_date) login_date
      FROM Traffic
      WHERE activity='login'
      GROUP BY user_id
  ) t
      WHERE datediff('2019-06-30',login_date) <= 90
      GROUP BY login_date;
  ```

- 查询每位学生获得的最高成绩和它所对应的科目,

  ```sql
  -- 查询每位学生获得的最高成绩和它所对应的科目，若科目成绩并列，取 course_id 最小的一门。查询结果需按 student_id 增序进行排序。
  
  Enrollments 表：
  +------------+-------------------+
  | student_id | course_id | grade |
  +------------+-----------+-------+
  | 2          | 2         | 95    |
  | 2          | 3         | 95    |
  | 1          | 1         | 90    |
  | 1          | 2         | 99    |
  | 3          | 1         | 80    |
  | 3          | 2         | 75    |
  | 3          | 3         | 82    |
  +------------+-----------+-------+
  
  Result 表：
  +------------+-------------------+
  | student_id | course_id | grade |
  +------------+-----------+-------+
  | 1          | 2         | 99    |
  | 2          | 2         | 95    |
  | 3          | 3         | 82    |
  +------------+-----------+-------+
  
  -- 思路：先找到每个同学Id和对应的最高分科目，然后再从满足条件的记录中筛选出符合条件的记录
  
  SELECT
  	student_id, min(course_id) as course_id, grade
  FROM
  	Enrollments
  WHERE (student_id,grade) IN (
      SELECT student_id, max(grade)
      FROM Enrollments
      GROUP BY student_id
  )
  GROUP BY student_id, grade
  ORDER BY student_id
  ```

- 查询活跃用户

  ```sql
  -- 如果一个业务的某个事件类型的发生次数大于此事件类型在所有业务中的平均发生次数，并且该业务至少有两个这样的事件类型，那么该业务就可被看做是活跃业务。
  
  Events 表:
  +-------------+------------+------------+
  | business_id | event_type | occurences |
  +-------------+------------+------------+
  | 1           | reviews    | 7          |
  | 3           | reviews    | 3          |
  | 1           | ads        | 11         |
  | 2           | ads        | 7          |
  | 3           | ads        | 6          |
  | 1           | page views | 3          |
  | 2           | page views | 12         |
  +-------------+------------+------------+
  
  Result 表：
  +-------------+
  | business_id |
  +-------------+
  | 1           |
  +-------------+ 
  
  -- 思路：求出每个event_type和对应的平均值，再与原表进行联结，然后筛选出满足条件的记录大于2的项目
  
  SELECT 
  	business_id
  FROM Events AS e
  JOIN (
      SELECT event_type, AVG(occurences) AS eventAvg
      FROM Events
      GROUP BY event_type
  ) AS e1 
  ON e.event_type = e1.event_type
  WHERE e.occurences > e1.eventAvg
  GROUP BY business_id
  HAVING COUNT(*) >= 2
  ```

- 被移除的帖子的每日平均占比

  ```sql
  -- 在被报告为垃圾广告的帖子中，被移除的帖子的每日平均占比
  
  Actions 表:
  +---------+---------+-------------+--------+--------+
  | user_id | post_id | action_date | action | extra  |
  +---------+---------+-------------+--------+--------+
  | 1       | 1       | 2019-07-01  | view   | null   |
  | 1       | 1       | 2019-07-01  | like   | null   |
  | 1       | 1       | 2019-07-01  | share  | null   |
  | 2       | 2       | 2019-07-04  | view   | null   |
  | 2       | 2       | 2019-07-04  | report | spam   |
  | 3       | 4       | 2019-07-04  | view   | null   |
  | 3       | 4       | 2019-07-04  | report | spam   |
  | 4       | 3       | 2019-07-02  | view   | null   |
  | 4       | 3       | 2019-07-02  | report | spam   |
  | 5       | 2       | 2019-07-03  | view   | null   |
  | 5       | 2       | 2019-07-03  | report | racism |
  | 5       | 5       | 2019-07-03  | view   | null   |
  | 5       | 5       | 2019-07-03  | report | racism |
  +---------+---------+-------------+--------+--------+
  
  Removals 表:
  +---------+-------------+
  | post_id | remove_date |
  +---------+-------------+
  | 2       | 2019-07-20  |
  | 3       | 2019-07-18  |
  +---------+-------------+
  
  Result 表:
  +-----------------------+
  | average_daily_percent |
  +-----------------------+
  | 75.00                 |
  +-----------------------+
  -- 2019-07-04 的垃圾广告移除率是 50%，因为有两张帖子被报告为垃圾广告，但只有一个得到移除。
  -- 2019-07-02 的垃圾广告移除率是 100%，因为有一张帖子被举报为垃圾广告并得到移除。
  -- 其余几天没有收到垃圾广告的举报，因此平均值为：(50 + 100) / 2 = 75%
  -- 注意，输出仅需要一个平均值即可，我们并不关注移除操作的日期。
  
  
  --思路：先找出每个日期和对应的垃圾广告移除率，再求平均移除率
  
  SELECT
  	ROUND(AVG(t.daily_percent) * 100, 2) average_daily_percent
  FROM(
  	SELECT 
      	count(distinct r.post_id) / count(distinct a.post_id) as daily_percent
  	from Actions a left join Removals r
  	on a.post_id = r.post_id
  	where a.extra = 'spam'
  	group by a.action_date
  ) as t
  ```

- 

- 

- 

- 

- 

- 

- 

- 

- 









## 常用函数

- `IFNULL(expr1，expr2)`， 如果`expr1`不为空则返回`expr1`，为空则返回`expr2`

- `IF(expr1，expr2，expr3)`，如果expr1为真，则返回expr2，为假则返回expr3

- `DATADIFF(time1，time2)`,  返回time1 - time2

- `CASE WHEN`,

  1. `CASE expr1 WHEN expr2 THEN expr3 WHEN expr4 THEN expr5 ELSE expr6 END`，如果expr1等于expr2返回expr3，等于expr4返回expr5，否则返回expr6
  2. `CASE WHEN expr2 THEN expr3 WHEN expr4 THEN expr5 END`，如果expr2为真则返回expr3，如果expr4为真则返回expr5

- `DATE_FORMAT(time, '%Y-%m')`，将time转换成 年-月 格式

- 自定义函数

  ```sql
  -- 自定义一个函数，返回第n高的数，如果不存在则返回null
  
  CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
  BEGIN
      SET N := N-1;
    RETURN (
        SELECT 
              salary
        FROM 
              employee
        GROUP BY 
              salary
        ORDER BY 
              salary DESC
        LIMIT N, 1
    );
  END
  ```
  
- 窗口函数

  ```
  
  ```

  



## 神奇操作

- `SUM(expr)`， 求出expr为真时的数目，等价于 `COUNT(IF(expr, 1, 0))`
- `AVG(statement is not null)`,求出statement中不为空的数占所有数的比例
- `time + 1`，表示time的后一天
- `COUNT`不会计算控制





## 特别注意

- `‘’(空值)`和`NULL`的区别

  1. 空值不占空间，NULL值占空间；
  2. 字段限制条件为`NOT NULL`时可以插入空值；
  3. `IS NOT NULL`只可以过滤`null`，`<> ''`可以同时过滤空值和`NULL`；
  4. `COUNT`统计时会过滤掉 `NULL` 值，但是不会过滤掉空值；

- 出现空值和`NULL`的情况

  ```sql
  -- 下面的查询如果不存在满足条件的记录时会输出空值
  SELECT num
  FROM my_numbers
  GROUP BY num
  HAVING COUNT(*) = 3
  ORDER BY num DESC
  limit 1
  
  
  -- 如果从空值中查找最大值会输出NULL，如下
  SELECT
      MAX(num) AS num
  FROM
  (
      SELECT num
      FROM my_numbers
      GROUP BY num
      HAVING COUNT(num) = 1
  ) AS t
  ```

- 





## 格式规范

```sql
SELECT
	a.visited_on,
	sum( b.amount ) AS amount,
	round(sum( b.amount ) / 7, 2 ) AS average_amount 
FROM
	( SELECT DISTINCT visited_on FROM customer ) a JOIN customer b 
 	ON datediff( a.visited_on, b.visited_on ) BETWEEN 0 AND 6 
WHERE
	a.visited_on >= (SELECT min( visited_on ) FROM customer ) + 6 
GROUP BY
	a.visited_on
```

- 关键字用大写
- `SELECT`分多行
- 括号两边空一格字符
- 短的内查询合为一行
- `JOIN`和`ON`放在一行
- 



## 参考文章

- [在MySQL中实现Rank高级排名函数](https://www.cnblogs.com/caicaizi/p/9803013.html)
-  [MySQL窗口函数简介](https://blog.csdn.net/qq_41080850/article/details/86416106)
-  