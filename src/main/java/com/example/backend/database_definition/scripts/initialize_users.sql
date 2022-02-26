create or replace function create_user_if_not_exists(_email text, _password text, _role text) returns void as
$$

begin
    if not exists(select id from auth_user.users where email = _email) then
        insert into auth_user.users(email, password, date_created, role_id)
        values (_email, _password, now(), find_role_id_by_name(_role));
    end if;
end;

$$ language plpgsql;

select create_user_if_not_exists('admin@hotmail.com', 'admin', 'ROLE_ADMIN'),
       create_user_if_not_exists('user@hotmail.com', 'user', 'ROLE_USER');


