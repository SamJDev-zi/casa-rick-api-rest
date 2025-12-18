INSERT INTO roles (role_name)
VALUES ('administrador'),
       ('empleado');

INSERT INTO permissions (permission_name)
VALUES 
('permiso_venta'),
('permiso_registrar_producto'),
('permiso_caja'),
('permiso_inventario'),
('permiso_impresion'),
('permiso_editar_venta'),
('permiso_editar_reserva'),
('permiso_reserva');


INSERT INTO users (user_name, user_last_name, user_phone_number, user_password, role_id)
VALUES ('roxana', 'ramirez', '60775608', 'admin123', 1);


INSERT INTO branches (branch_name, branch_address, branch_phone, is_active)
values ('Sucursal de prueba', 'San martin', '464686', true) ;