INSERT INTO clients (first_name, last_name, email, password)
VALUES ('Ján', 'Novák', 'jan@example.com', 'heslo123'),
       ('Mária', 'Svoboda', 'maria@example.com', 'heslo456')
    ON CONFLICT DO NOTHING;