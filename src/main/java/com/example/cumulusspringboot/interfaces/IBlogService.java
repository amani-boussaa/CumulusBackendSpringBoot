package com.example.cumulusspringboot.interfaces;

        import com.example.cumulusspringboot.entities.Blog;

        import java.sql.Blob;
        import java.util.List;

public interface IBlogService {
    Blog CreateBlog (Blog B);

    List<Blog> ReadBlog ();

    Blog UpdateBlog(Blog B);

    void DeleteBlog(Long id);

    Blog RetrieveBlog(Long id);

    List<Blog> getBlogByIdUser(Long iduser);
}
//declaration ds methodes