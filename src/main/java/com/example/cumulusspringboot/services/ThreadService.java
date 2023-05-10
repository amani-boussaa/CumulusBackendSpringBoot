package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.entities.*;
import com.example.cumulusspringboot.entities.Thread;
import com.example.cumulusspringboot.interfaces.IThreadService;
import com.example.cumulusspringboot.repositories.*;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
public class ThreadService implements IThreadService {
    @Autowired
    ThreadRepo threadRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    UserActivityRepo userActivityRepo;
    @Autowired
    ThreadTagRepo threadTagRepo;
    @Autowired
    CommentRepository commentRepo;
    @PersistenceContext
    private EntityManager em;


//    public List<Thread> recommendThreadsToUser(Long userId) {
//        // Find the user's useractivity records
//        List<UserActivity> userActivityList = userActivityRepo.findByauserId(userId);
//
//        // Group the useractivity records by thread tag and calculate the total view count for each tag
//        Map<ThreadTag, Integer> threadTagViewCountMap = new HashMap<>();
//        for (UserActivity userActivity : userActivityList) {
//            ThreadTag threadTag = userActivity.getThreadTag();
//            Integer viewCount = threadTagViewCountMap.getOrDefault(threadTag, 0);
//            threadTagViewCountMap.put(threadTag, viewCount + userActivity.getViewCount());
//        }
//
//        // Find the top 5 threads for each tag that the user has viewed
//        List<Thread> recommendedThreads = new ArrayList<>();
//        for (Map.Entry<ThreadTag, Integer> entry : threadTagViewCountMap.entrySet()) {
//            ThreadTag threadTag = entry.getKey();
//            List<Thread> threadsWithTag = threadRepo.findByThreadTagsOrderByViewCountDesc(threadTag);
//            recommendedThreads.addAll(threadsWithTag.subList(0, Math.min(5, threadsWithTag.size())));
//        }
//
//        return recommendedThreads;
//    }


    @Override
    public Thread createThread(Thread thread,Long userID) {
        List<Thread> userThreads = threadRepo.findByThreadCreatorId(userID);
        int nbr_TotalThreads = userThreads.size();
        User user = userRepo.findById(userID).orElse(null);
        Wallet wallet = user.getWallet();
        System.out.println(wallet.getSubscription());
        if ((wallet.getSubscription().equals("None")) && nbr_TotalThreads==10 )
        {
            throw new IllegalStateException("You reach the limit of posts.");
        }
        else {
            return threadRepo.save(thread);
        }

    }

    @Override
    public List<Thread> getAllThreads() {
        return threadRepo.findAll();
    }

    @Override
    public List<Thread> getThreadByUser(Long userID) {
        return threadRepo.findByThreadCreatorId(userID) ;
    }

    @Override
    public void getAllThre(MultipartFile file) {
        try {
            System.out.println(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void viewThread(long threadId, long userId) {


//        Thread thread = threadRepo.findById(threadId).orElse(null);
//        if (thread != null) {
//            List<ThreadTag> tags = thread.getThreadTags();
//
//
//            UserActivity userActivity = new UserActivity();
//              User  user = userRepo.findById(userId).orElse(null);
//            System.out.println(user.toString());
//            if (user != null) {
//
//                if(userActivity!=null) {
//                    System.out.println("USER ACTIVITY NOT NULL");
//                    List<TagCount> tagCounts = userActivity.getTagCounts();
//                    if (tagCounts == null) {
//                        tagCounts = new ArrayList<>();
//                    }
//                    if (userActivity == null) {
//                        userActivity = new UserActivity();
//
//
//                        for (ThreadTag tag : tags) {
//                            System.out.println(tag);
//                            tagCounts.add(new TagCount(1, tag.getName()));
//                        }
//                        userActivity.setTagCounts(tagCounts);
//                    }else{
//                        userActivity.setAuser(user);
//                    for (ThreadTag tag : tags) {
//
//                        incrementTagCount(tag.getName(), tagCounts);
//                    }}
//
//                    userActivity.setTagCounts(tagCounts);
//                    System.out.println(userActivity);
//                    userActivityRepo.save(userActivity);
//                    System.out.println("VIEW THREAD SAVED");
//                }
//            } else {
//                System.out.println("VIEW THREAD ELSE (USER  NULL )");
//
//
//               /* UserActivity userActivity = new UserActivity();
//                userActivity.setSessionId(sessionId);
//                userActivity.setActivityType("view_thread");
//                userActivity.setActivityData(String.valueOf(threadId));
//                userActivity.setTagCounts(new HashMap<>());
//                Map<String, Integer> tagCounts = userActivity.getTagCounts();
//                for (String tag : tags) {
//                    tagCounts.put(tag, tagCounts.getOrDefault(tag, 0) + 1);
//                }
//                userActivityRepository.save(userActivity);*/
//            }
//        }



    }

    @Override
    public Thread addTagToThread(long threadId, List<ThreadTag> threadTags) {
    Thread      thread=  threadRepo.findById(threadId).get();
        List<ThreadTag>tags = thread.getThreadTags();
        for (ThreadTag t :threadTags
             ) {

           tags.add(t);

        };thread.setThreadTags(tags);
        return thread;
    }

    @Override
    public Thread createThreadWithTags(Thread thread, List<String> tagNames) {
//        Thread savedThread = threadRepo.save(thread);

        // Set the thread ID on each thread tag entity
//        threadTags.forEach(tag -> tag.getThreadT().add(savedThread));
/*
        threadTags.forEach(tag -> {
            if (tag.getThreadT()==null) {
                List<Thread> lt =new ArrayList<>();
                lt.add(savedThread);
                tag.setThreadT(lt);
            }else {
                tag.getThreadT().add(savedThread);
                {

                }
            }});
        // Save the thread tag entities
        threadTagRepo.saveAll(threadTags);

    return   savedThread;*/
        List<ThreadTag> tags = new ArrayList<>();

        for (String tagName : tagNames) {
            ThreadTag tag = threadTagRepo.findByName(tagName);
            if (tag == null) {
                tag = new ThreadTag(tagName);
                threadTagRepo.save(tag);
            }
            tags.add(tag);
        }

        List<ThreadTag> threadTags = thread.getThreadTags();
        if (threadTags == null) {
            threadTags = new ArrayList<>();
            thread.setThreadTags(threadTags);
        }
        threadTags.addAll(tags);

        Thread savedThread = threadRepo.save(thread);

        for (ThreadTag tag : tags) {
            tag.getThreadT().add(savedThread);
            threadTagRepo.save(tag);
        }

        return savedThread;
    }

    @Transactional
    @Override
    public void deleteThread(long id) {
        Optional<Thread> optionalThread = threadRepo.findById(id);
        if (optionalThread.isPresent()) {
            Thread thread = optionalThread.get();
            threadRepo.deleteThreadWithAssociations(thread.getId());
        } else {
            throw new EntityNotFoundException("Thread with id " + id + " not found");
        }
    }

    @Override
    public ArrayList ThreadStats(long userID) {
        ArrayList total = new ArrayList<>();
        List<Thread> userThreads = threadRepo.findByThreadCreatorId(userID);
            List<Comment> comments=commentRepo.findByuserId(userID);
            int nbr_TotalThreads = userThreads.size();
            int nbr_TotalComments = comments.size();
        System.out.println("nbr_TotalComments"+nbr_TotalComments);
total.add(nbr_TotalThreads);
total.add(nbr_TotalComments);

        return total ;
    }

    @Override
    public Thread getAllComments(Long threadId) {
//List<Comment> cs = null;
//        for (Comment c :threadRepo.findById(threadId).get().getComments() ) {
//            System.out.println("aaaaaaaaaaaaa"+c);
//cs.add(c);
//
//        }
            return threadRepo.findById(threadId).get() ;

    }

    @Override
    public Thread addCommentToThread(long threadId,Comment comment,Long userid) {
        Thread thread =threadRepo.findById(threadId).get();
        comment.setUser(userRepo.findById(userid).get());

        thread.getComments().add(comment);





        return threadRepo.save(thread) ;
    }








    public List<ThreadTag> getMostViewedThreadTagsForUser(Long userId, int limit) {
        String query = "SELECT ua.threadTag, SUM(ua.viewCount) as totalViews " +
                "FROM UserActivity ua " +
                "WHERE ua.auser.id = :userId " +
                "GROUP BY ua.threadTag " +
                "ORDER BY totalViews DESC";
        TypedQuery<Object[]> q = em.createQuery(query, Object[].class);
        q.setParameter("userId", userId);
        q.setMaxResults(limit);
        List<Object[]> results = q.getResultList();
        List<ThreadTag> tags = new ArrayList<>();
        for (Object[] result : results) {
            ThreadTag tag = (ThreadTag) result[0];
            tags.add(tag);
        }
        return tags;
    }

    public List<Thread> getRecommendedThreads(List<ThreadTag> tags, int limit) {
        String query = "SELECT t, SUM(ua.viewCount) as totalViews " +
                "FROM Thread t " +
                "JOIN t.threadTags tt " +
                "JOIN tt.userActivities ua " +
                "WHERE tt IN :tags " +
                "GROUP BY t " +
                "ORDER BY totalViews DESC";
        TypedQuery<Object[]> q = em.createQuery(query, Object[].class);
        q.setParameter("tags", tags);
        q.setMaxResults(limit);
        List<Object[]> results = q.getResultList();
        List<Thread> threads = new ArrayList<>();
        for (Object[] result : results) {
            Thread thread = (Thread) result[0];
            threads.add(thread);
        }
        return threads;

    }

}
