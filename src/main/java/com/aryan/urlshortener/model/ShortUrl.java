package com.aryan.urlshortener.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="short_urls")
public class ShortUrl {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String originalUrl;

    @Column(nullable=false, unique=true)
    private String shortUrl;

    @Column(nullable = false)
    private Integer clickCount;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @PrePersist
    protected void onCreate()
    {
        LocalDateTime now=LocalDateTime.now();
        this.createdAt=now;
        this.updatedAt=now;

        if(this.clickCount == null)
        {
            this.clickCount=0;
        }
    }

    @PreUpdate
    protected void onUpdate()
    {
        this.updatedAt=LocalDateTime.now();
    }

    public Long getId()
    {
        return id;
    }

    public String getOriginalUrl()
    {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl)
    {
        this.originalUrl=originalUrl;
    }

    public String getShortUrl()
    {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl)
    {
        this.shortUrl=shortUrl;
    }

    public Integer getClickCount()
    {
        return clickCount;
    }

    public void setClickCount(Integer clickCount)
    {
        this.clickCount=clickCount;
    }

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt()
    {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt()
    {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt)
    {
        this.deletedAt=deletedAt;
    }
}

//d, created_at, updated_at, deleted_at, originalUrl, shortUrl, hitNo