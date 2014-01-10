package com.github.ambry.coordinator;

/**
 * Errors that the Coordinator returns
 */
public enum CoordinatorError {
  // TODO: Add OperationError enumeration to bin UnexpectedInternalErrors

  // General errors. May occur for any operation.
  /**
   * Coordinator experienced an unexpected internal error. This means that there is either a transient issue with a
   * specific node or with networking, or that there is a bug in the coordinator implementation. The caller should retry
   * the operation. An operation that changes the state of an existing blob (delete, cancelTTL) may have partially
   * completed and so may eventually complete in the future. A put operation may also have partially completed and so
   * may eventually "complete", however the BlobId was never returned to the caller and so the blob will never be
   * retrieved or deleted.
   */
  UnexpectedInternalError,
  /**
   * Insufficient Ambry DataNodes could be contacted to complete an operation. The caller should retry the operation. An
   * operation that changes the state of an existing blob (delete, cancelTTL) may have partially completed and so may
   * eventually complete in the future. A put operation may also have partially completed and so may eventually
   * "complete", however the BlobId was never returned to the caller and so the blob will never be retrieved or
   * deleted.
   */
  AmbryUnavailable,
  /**
   * Operation did not complete within specified time out. The caller should retry the operation. An operation that
   * changes the state of an existing blob (delete, cancelTTL) may have partially completed and so may eventually
   * complete in the future. A put operation may also have partially completed and so may eventually "complete", however
   * the BlobId was never returned to the caller and so the blob will never be retrieved or deleted.
   */
  OperationTimedOut,

  // Errors on write path. May occur for put operations.
  /**
   * Insufficient capacity available in Ambry for object to be stored.
   */
  InsufficientCapacity,
  /**
   * Blob is too large. Cannot store blob of such size.
   */
  BlobTooLarge,

  // Errors on read path. May occur for getBlobProperties, getBlobUserMetadata, or getBlob operations.
  /**
   * No Blob could be found for specified blob id.
   */
  BlobDoesNotExist,
  /**
   * Blob has been deleted and so cannot be retrieved.
   */
  BlobDeleted,
  /**
   * TTL of Blob has expired and so Blob cannot be retrieved.
   */
  BlobExpired
}
