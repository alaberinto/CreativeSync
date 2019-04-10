/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Matthew
 * Created: Apr 8, 2019
 *
 * This script is used for testing rounds & artwork on the artwork detailed page. More artwork is provided to test functionality.
 */

-- insert into artwork
-- values (4,'The fourth artwork','The fourth artwork reference',75,0,2,1,4,2);

-- insert into artwork
-- values (5,'The fifth artwork','The fifth artwork reference',75,0,3,1,6,1);
-- --
-- insert into artwork
-- values (6,'The sixth artwork','The sixth artwork reference',75,0,4,1,6,1);

-- insert into artwork
-- values (7,'The seventh artwork','The sixth artwork reference',75,0,1,2,6,1);

-- insert into TITLE_has_ACCOUNT (ACCOUNT_user_id,TITLE_title_id,STATUS_status_id)
-- values (10,2,1);
-- 
-- insert into artwork
-- values (8,'The eight artwork','The eight artwork reference',75,0,1,2,10,1);

insert into artwork
values (9,'The ninth artwork','The ninth artwork reference',75,0,1,2,10,1);


commit;