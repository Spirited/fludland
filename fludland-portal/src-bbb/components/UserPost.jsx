import React from "react";
import "../css/UserPost.css"

const UserPost = ({ avatar, nickname, title, content, likes, reposts, views, comments}) => {
    return (
        <div className="post-container">
            <div className="post-header">
                <img src={avatar} alt={`${nickname}'s avatar`} className="post-avatar"/>
                <span className="post-nickname">{nickname}</span>
            </div>
            <div className="post-body">
                <h3 className="post-title">{title}</h3>
                <p className="post-content">{content}</p>
            </div>
            <div className="post-footer">
                <div className="post-footer-item">
                    👍 <span>{likes}</span>
                </div>
                <div className="post-footer-item">
                    🔄 <span>{reposts}</span>
                </div>
                <div className="post-footer-item">
                    👁 <span>{views}</span>
                </div>
                <div className="post-footer-item">
                    💬 <span>{comments}</span>
                </div>
            </div>
        </div>
    );
};

export default UserPost;