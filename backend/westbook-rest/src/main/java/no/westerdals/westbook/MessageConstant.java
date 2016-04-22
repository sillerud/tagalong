package no.westerdals.westbook;

public enum MessageConstant
{
    // User
    USER_NOT_FOUND,
    USER_DELETED,
    USER_CREATED,
    USER_UPDATED,

    // Post
    POST_CREATED,
    POST_NOT_FOUND,
    POST_DELETED,
    POST_NO_CONTENT_PROVIDED,
    POST_NO_TAGS_PROVIDED,

    // Comment
    COMMENT_CREATED,
    COMMENT_DELETED,

    // File
    FILE_UPLOADED,
    FILE_NOT_FOUND,

    // Feed,
    FEED_DELETED,
    FEED_CREATED,

    // Unmapped error,
    UNMAPPED_ERROR,

    // Link
    LINK_CREATED,

    // Post,
    PAGE_CREATED,
    PAGE_UPDATED,
    PAGE_URL_ALREADY_EXISTS_ERROR,
    PAGE_NOT_FOUND,
    PAGE_DELETED,

    // Cards
    CARD_CREATED,
    CARD_NOT_FOUND,
    CARD_DELETED,

    // Events
    EVENT_CREATED,
    EVENT_UPDATED,
    EVENT_NOT_FOUND,
    EVENT_DELETED,

    //
    UPVOTED,
    UPVOTE_REMOVED,
    ALREADY_UPVOTED,

    // General
    NOT_IMPLEMENTED,
    ACCESS_DENIED,
}
