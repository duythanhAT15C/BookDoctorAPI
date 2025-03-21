package com.lab.Assignment03.Service;

import com.lab.Assignment03.DTO.PostDTO;
import com.lab.Assignment03.Entity.Comments;

import java.util.List;

public interface PostsService {
    public boolean save(PostDTO postDTO);
    public List<PostDTO> getListPostsByDoctorUsersId(int doctorUserId);
    public PostDTO getPostsByDoctorUsersIdAndPostsId(int id, int doctorId);
    public boolean updateConfirmByDoctor(int id, int doctorUserId, int check);
    public PostDTO getPostById(int id);
    public PostDTO getPostDTOByDoctorUsersIdAndWriterId(int doctorUsersId, int writerId);
    public void deletePosts(PostDTO postDTO);
}
