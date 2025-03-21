package com.lab.Assignment03.ServiceImpl;

import com.lab.Assignment03.Convert.ConvertToDTO;
import com.lab.Assignment03.Convert.ConvertToEntity;
import com.lab.Assignment03.DTO.PostDTO;
import com.lab.Assignment03.Entity.*;
import com.lab.Assignment03.Repository.*;
import com.lab.Assignment03.Service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {
    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private ConvertToEntity convertToEntity;
    @Autowired
    private ClinicsRepository clinicsRepository;
    @Autowired
    private DoctorUsersRepository doctorUsersRepository;
    @Autowired
    private SpecializationRepository specializationRepository;
    @Autowired
    private ConvertToDTO convertToDTO;
    @Autowired
    private UserRepository userRepository;
    @Override
    public boolean save(PostDTO postDTO) {
        Posts posts = convertToEntity.convertPosts(postDTO);
        Clinics clinics = clinicsRepository.findById(postDTO.getClinicsDTO().getId());
        DoctorUsers doctorUsers = doctorUsersRepository.getDoctorUser(postDTO.getDoctorUsersDTO().getId());
        Specialization specialization = specializationRepository.findById(postDTO.getSpecializationDTO().getId());
        Users users = userRepository.findById(postDTO.getUserDTO().getId());
        posts.setDoctorUsers(doctorUsers);
        posts.setSpecialization(specialization);
        posts.setUsers(users);
        posts.setClinics(clinics);
        posts.setConfirmByDoctor(-1);
        postsRepository.save(posts);
        return true;
    }

    @Override
    public List<PostDTO> getListPostsByDoctorUsersId(int doctorUserId) {
        DoctorUsers doctorUsers = doctorUsersRepository.getDoctorUserByDoctorUserId(doctorUserId);
        List<Posts> listPosts = postsRepository.getPostsByDoctorUsersId(doctorUsers.getId());
        List<PostDTO> postDTOS = new ArrayList<>();
        for(Posts posts : listPosts){
            if(posts.getConfirmByDoctor() == -1){
                postDTOS.add(convertToDTO.convertPost(posts));
            }

        }
        return postDTOS;
    }

    @Override
    public PostDTO getPostsByDoctorUsersIdAndPostsId(int id, int doctorId) {
        Posts posts = postsRepository.getPostsByDoctorUsersIdAndPostId(doctorId, id);
        if(posts == null){
            return null;
        }
        return convertToDTO.convertPost(posts);
    }

    @Override
    public boolean updateConfirmByDoctor(int id, int doctorUserId, int check) {
        Posts posts = postsRepository.getPostsByDoctorUsersIdAndPostId(doctorUserId, id);
        if (posts == null) {
            return false;
        } else {
            if(posts.getConfirmByDoctor() == 1 && check == 1){
                return false;
            }
            if(posts.getConfirmByDoctor() == 0 && check == 0){
                return false;
            }
            if(check == 1){
                posts.setConfirmByDoctor(1);
            }
            else if (check == 0){
                posts.setConfirmByDoctor(0);
            }
            posts.setUpdatedAt(LocalDateTime.now());
            postsRepository.save(posts);
        }
        return true;
    }

    @Override
    public PostDTO getPostById(int id) {
        Posts posts = postsRepository.findById(id);
        return convertToDTO.convertPost(posts);
    }

    @Override
    public PostDTO getPostDTOByDoctorUsersIdAndWriterId(int doctorUsersId, int writerId) {
        Posts posts = postsRepository.getPostsByDoctorUsersIdAndWriterId(doctorUsersId, writerId);
        if (posts == null){
            return null;
        }
        return convertToDTO.convertPost(posts);
    }

    @Override
    public void deletePosts(PostDTO postDTO) {
        postsRepository.delete(convertToEntity.convertPosts(postDTO));
    }
}
