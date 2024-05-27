package demo.module.user;

import java.util.ArrayList;

public interface UserRepository {
    // lấy tất cả trong database.
    ArrayList<User> findAll();
    // lấy theo id truyền vào.
    User findById(Long id);
    // lưu vào database
    User save(User user);
    // cập nhật thông tin trong database
    User update(User user);
    // xóa bản ghi
    void deleteById(Long id);
}
