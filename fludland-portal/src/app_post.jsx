import React from "react";
import UserPost from "./components/UserPost.jsx";

function App_post() {
    const samplePost = {
        avatar: "https://via.placeholder.com/48",
        nickname: "John Smith",
        title: "First post",
        content: "This is the first post",
        likes: 42,
        reposts: 32,
        views: 123,
        comments: 5
    };

    return (
        <div>
            <UserPost
                avatar={samplePost.avatar}
                nickname={samplePost.nickname}
                title={samplePost.title}
                content={samplePost.content}
                comments={samplePost.comments}
                likes={samplePost.likes}
                reposts={samplePost.reposts}
                views={samplePost.views}
            />
        </div>
    )
};

export default App_post;